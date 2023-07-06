package com.example.carrito.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class DetalleCarritoId implements Serializable {
    @Column(name = "carrito_id")
    private Integer carritoId;

    @Column(name = "producto_id")
    private Integer productoId;
}
