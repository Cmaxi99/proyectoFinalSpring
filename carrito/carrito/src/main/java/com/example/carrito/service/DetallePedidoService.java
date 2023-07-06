package com.example.carrito.service;

import com.example.carrito.persistence.entities.DetallePedido;
import com.example.carrito.persistence.entities.DetallePedidoId;
import com.example.carrito.persistence.entities.Pedido;
import com.example.carrito.persistence.entities.Producto;
import com.example.carrito.persistence.repository.DetallePedidoRepository;
import com.example.carrito.persistence.repository.PedidoRepository;
import com.example.carrito.persistence.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetallePedidoService {
    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProductoRepository productoRepository;

    public String guardarDetalle(DetallePedido detallePedido, Integer pedidoId){

        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        Optional<Producto> producto = productoRepository.findById(detallePedido.getId().getProductoId());
        detallePedido.setPedido(pedido.get());
        detallePedido.setProducto(producto.get());
        if (detallePedidoRepository.save(detallePedido) != null) {
            return "OK";
        }

        return null;
    }

    public String eliminarDetalle(DetallePedidoId id){
        if (detallePedidoRepository.findById(id).isPresent()){
            detallePedidoRepository.deleteById(id);
            return "OK";
        }
        return null;
    }
}
