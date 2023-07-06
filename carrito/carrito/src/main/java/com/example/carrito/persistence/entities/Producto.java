package com.example.carrito.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String categoria;

    @Column
    private String descripcion;

    @Column
    private int tamanio;

    @Column
    private Float precio;

    @Column
    private boolean estado;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallePedidos;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<DetalleCarrito> detalleCarritos;
}
