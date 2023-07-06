package com.example.carrito.persistence.repository;

import com.example.carrito.persistence.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
