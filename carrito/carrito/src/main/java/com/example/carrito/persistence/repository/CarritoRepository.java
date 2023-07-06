package com.example.carrito.persistence.repository;

import com.example.carrito.persistence.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}
