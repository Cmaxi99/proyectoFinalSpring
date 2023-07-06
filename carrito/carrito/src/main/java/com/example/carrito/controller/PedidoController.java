package com.example.carrito.controller;

import com.example.carrito.persistence.entities.Pedido;
import com.example.carrito.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;
    @GetMapping("/listadoPedidos")
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }

    @PostMapping("/registrarPedido")
    public ResponseEntity<String> registrar(@RequestBody Pedido pedido){
        ResponseEntity<String> respuesta = ResponseEntity.internalServerError().body("El pedido no pudo ser registrado correctamente.");

        if (pedidoService.guardar(pedido) != null){
            respuesta = ResponseEntity.ok("El pedido fue registrado con exito.");
        }

        return respuesta;
    }

    @GetMapping("/listarPedido/{id}")
    public ResponseEntity<Object>listarPedidoPorId(@PathVariable Integer id){
        ResponseEntity<Object> respuesta = ResponseEntity.badRequest().body("No se pudo encontrar el pedido con id = " + id + ".");

        if (pedidoService.buscarPedidoPorId(id).isPresent()){
            respuesta = ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
        }

        return respuesta;
    }

    @DeleteMapping("/eliminarPedido/{id}")
    public ResponseEntity<String>eliminarPedido(@PathVariable Integer id){
        ResponseEntity<String> respuesta = ResponseEntity.badRequest().body("No se pudo eliminar el pedido con id = " + id + ".");
        if (pedidoService.eliminarPedido(id)){
            respuesta = ResponseEntity.ok("El pedido con id = " + id + " se elimino correctamente.");
        }
        return respuesta;
    }

}
