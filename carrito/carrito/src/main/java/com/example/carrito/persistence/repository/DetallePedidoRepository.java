package com.example.carrito.persistence.repository;

import com.example.carrito.persistence.entities.DetallePedido;
import com.example.carrito.persistence.entities.DetallePedidoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoId> {
}
