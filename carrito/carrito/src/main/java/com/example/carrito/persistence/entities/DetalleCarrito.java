package com.example.carrito.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_carrito")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetalleCarrito {

    @EmbeddedId
    private DetalleCarritoId id;

    @JsonIgnore
    @MapsId("carritoId")
    @ManyToOne
    private Carrito carrito;

    @JsonIgnore
    @MapsId("productoId")
    @ManyToOne
    private Producto producto;

    @Column
    private int cantidad;
}
