package com.example.carrito.persistence.repository;

import com.example.carrito.persistence.entities.DetalleCarrito;
import com.example.carrito.persistence.entities.DetalleCarritoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, DetalleCarritoId> {
}
