package com.example.carrito.service;

import com.example.carrito.persistence.entities.Carrito;
import com.example.carrito.persistence.entities.DetalleCarrito;
import com.example.carrito.persistence.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    DetalleCarritoService detalleCarritoService;

    public String guardar(Carrito carrito){
        if (carritoRepository.save(carrito) != null){
            List<DetalleCarrito> detalleCarrito = carrito.getDetalleCarritos();
            for (DetalleCarrito detalle : detalleCarrito){
                detalleCarritoService.guardarDetalle(detalle, carrito.getId());
            }
            carritoRepository.save(carrito);
            return "OK";
        }
        return null;
    }

    public List<Carrito> obtenerCarrito(){
        return carritoRepository.findAll();
    }

}
