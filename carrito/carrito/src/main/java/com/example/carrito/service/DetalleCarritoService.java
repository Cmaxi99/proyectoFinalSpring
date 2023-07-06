package com.example.carrito.service;

import com.example.carrito.persistence.entities.*;
import com.example.carrito.persistence.repository.CarritoRepository;
import com.example.carrito.persistence.repository.DetalleCarritoRepository;
import com.example.carrito.persistence.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetalleCarritoService {

    @Autowired
    DetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    ProductoRepository productoRepository;

    public String guardarDetalle(DetalleCarrito detalleCarrito, Integer carritoId){

        Optional<Carrito> carrito = carritoRepository.findById(carritoId);
        Optional<Producto> producto = productoRepository.findById(detalleCarrito.getId().getProductoId());
        detalleCarrito.setCarrito(carrito.get());
        detalleCarrito.setProducto(producto.get());

        if (detalleCarritoRepository.save(detalleCarrito) != null) {
            return "OK";
        }
        return null;
    }

    public Optional<DetalleCarrito> buscarDetallePorId(DetalleCarritoId id){return detalleCarritoRepository.findById(id);}

    public boolean modificarProducto(DetalleCarritoId id){return buscarDetallePorId(id).isPresent();}

    public void limpiarCarrito(){detalleCarritoRepository.deleteAll();}

    public void quitarProducto(DetalleCarritoId id){
        detalleCarritoRepository.deleteById(id);
    }

    public <T> T existencia (T parametroActualizado, T parametroActualizar){
        if (parametroActualizado != null){
            if (!parametroActualizado.equals(0)){
                return parametroActualizado;
            }
        }
        return parametroActualizar;
    }
}
