package com.example.carrito.persistence.repository;

import com.example.carrito.persistence.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
