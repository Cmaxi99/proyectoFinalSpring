package com.example.carrito.controller;

import com.example.carrito.persistence.entities.DetalleCarrito;
import com.example.carrito.persistence.entities.DetalleCarritoId;
import com.example.carrito.persistence.entities.Producto;
import com.example.carrito.service.DetalleCarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/detalleCarrito")
public class DetalleCarritoController {
    @Autowired
    DetalleCarritoService detalleCarritoService;

    @PostMapping("/agregarProducto")
    public ResponseEntity<String> agregarProducto(@RequestBody DetalleCarrito detalleCarrito)
    {
        ResponseEntity<String> respuesta = ResponseEntity.badRequest().body("No se pudo agregar el producto al carrito.");
        if (detalleCarritoService.guardarDetalle(detalleCarrito, 1) != null){
            respuesta = ResponseEntity.ok("Se agrego el producto correctamente.");
        }
        return respuesta;
    }

    @DeleteMapping("/quitarProducto/{id}")
    public ResponseEntity<String> quitarProducto(@PathVariable Integer id){
        ResponseEntity<String> respuesta = ResponseEntity.badRequest().body("El producto no pudo ser quitado del carrito.");
        DetalleCarritoId idCarrito = new DetalleCarritoId();
        idCarrito.setCarritoId(1);
        idCarrito.setProductoId(id);
        if (detalleCarritoService.buscarDetallePorId(idCarrito).isPresent()){
            detalleCarritoService.quitarProducto(idCarrito);
            respuesta = ResponseEntity.ok("Se quito el producto del carrito correctamente.");
        }

        return respuesta;
    }

    @DeleteMapping("/limpiarCarrito")
    public ResponseEntity<String> limpiarCarrito(){
        ResponseEntity<String> respuesta = ResponseEntity.ok("Se quitaron todos los productos del carrito.");
        detalleCarritoService.limpiarCarrito();
        return respuesta;
    }

    @PutMapping("/modificarCarrito")
    public ResponseEntity<String> modificarCarrito(@RequestBody DetalleCarrito detalleCarritoActualizado){
        ResponseEntity<String> respuesta = ResponseEntity.badRequest().body("No se pudo modificar el detalle.");
        Optional<DetalleCarrito> detalleCarrito = Optional.empty();
        if(detalleCarritoService.modificarProducto(detalleCarritoActualizado.getId())){
            detalleCarrito = detalleCarritoService.buscarDetallePorId(detalleCarritoActualizado.getId());
            detalleCarrito.get().setCantidad(detalleCarritoService.existencia(detalleCarritoActualizado.getCantidad(), detalleCarrito.get().getCantidad()));
            detalleCarritoService.guardarDetalle(detalleCarrito.get(), detalleCarrito.get().getCarrito().getId());
            respuesta = ResponseEntity.ok("Se modifico el detalle correctamente");
        }

        return respuesta;
    }
}
