package com.example.carrito.service;

import com.example.carrito.persistence.entities.DetallePedido;
import com.example.carrito.persistence.entities.Pedido;
import com.example.carrito.persistence.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    DetallePedidoService detallePedidoService;

    public String guardar(Pedido pedido){
        if(pedidoRepository.save(pedido) != null){
            List<DetallePedido> detallePedidos = pedido.getDetallePedidos();
            float totalPedido = 0;
            for (DetallePedido detalle: detallePedidos) {
                detallePedidoService.guardarDetalle(detalle, pedido.getId());
                totalPedido += detalle.getProducto().getPrecio() * detalle.getCantidad();
            }
            pedido.setTotal(totalPedido);
            pedidoRepository.save(pedido);
            return "OK";
        }
        return null;
    }

    public List<Pedido> obtenerTodos(){
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPedidoPorId(Integer id){return pedidoRepository.findById(id);}

    public boolean eliminarPedido(Integer id){
        if (buscarPedidoPorId(id).isPresent()){
            for (DetallePedido detalle: buscarPedidoPorId(id).get().getDetallePedidos())
            {
                detallePedidoService.eliminarDetalle(detalle.getId());
            }
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
