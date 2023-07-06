package com.example.carrito.controller;

import com.example.carrito.persistence.entities.Producto;
import com.example.carrito.service.ProductoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @PostMapping("/registrarProducto")
    public ResponseEntity<String> registrar(@RequestBody Producto producto){
        ResponseEntity<String> respuesta = ResponseEntity.internalServerError().body("El producto no pudo ser registrado correctamente.");
        if (productoService.guardar(producto) != null){
            respuesta = ResponseEntity.ok("El producto fue registrado con exito.");
        }

        return respuesta;
    }

    @GetMapping("/listadoProductos")
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/listarProducto/{id}")
    public ResponseEntity<Object> listarProductoPorId(@PathVariable Integer id){
        ResponseEntity<Object> respuesta = ResponseEntity.badRequest().body("No se pudo encontrar el producto con id = " + id + ".");

        if (productoService.buscarProductoPorId(id).isPresent()){
            respuesta = ResponseEntity.ok(productoService.buscarProductoPorId(id));
        }

        return respuesta;
    }

    @DeleteMapping("/desactivarProducto/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id){
        ResponseEntity<String> respuesta = ResponseEntity.badRequest().body("No se pudo desactivar el producto con id = " + id + ".");
        if(productoService.eliminarProducto(id)){
            respuesta = ResponseEntity.ok("Se desactivo el producto con id = " + id + " correctamente.");
        }
        return respuesta;
    }

    @PutMapping("/reactivarProducto/{id}")
    public ResponseEntity<String> reactivarProducto(@PathVariable Integer id){
        ResponseEntity<String> respuesta = ResponseEntity.badRequest().body("No se pudo reactivar el producto con id = " + id + ".");
        if (productoService.reactivarProducto(id)){
            respuesta = ResponseEntity.ok("Se reactivo el producto con id = " + id + " correctamente.");
        }
        return respuesta;
    }

    @PutMapping("/modificarProducto/{id}")
    public ResponseEntity<String> modificarProducto(@PathVariable Integer id, @RequestBody Optional<Producto> productoActualizado){
        ResponseEntity<String> respuesta = ResponseEntity.badRequest().body("El producto con id = " + id + " no puede ser modificado porque no existe en la base de datos.");
        Optional<Producto> producto = Optional.empty();

        if (productoService.modificarProducto(id)){
            producto = productoService.buscarProductoPorId(id);
            producto.get().setNombre(productoService.existencia(productoActualizado.get().getNombre(), producto.get().getNombre()));
            producto.get().setCategoria(productoService.existencia(productoActualizado.get().getCategoria(), producto.get().getCategoria()));
            producto.get().setDescripcion(productoService.existencia(productoActualizado.get().getDescripcion(), producto.get().getDescripcion()));
            producto.get().setTamanio(productoService.existencia(productoActualizado.get().getTamanio(), productoActualizado.get().getTamanio()));
            producto.get().setPrecio(productoService.existencia(productoActualizado.get().getPrecio(), producto.get().getPrecio()));
            producto.get().setEstado(productoService.existencia(productoActualizado.get().isEstado(), producto.get().isEstado()));
            productoService.guardar(producto.get());
            respuesta = ResponseEntity.ok("El producto con id = " + id + " fue modificado correctamente.");
        }

        return respuesta;
    }

}
