package com.example.carrito.controller;

import com.example.carrito.persistence.entities.Carrito;
import com.example.carrito.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    @GetMapping("/listarCarrito")
    public ResponseEntity<List<Carrito>> listarCarrito() {
        return ResponseEntity.ok(carritoService.obtenerCarrito());
    }

}
