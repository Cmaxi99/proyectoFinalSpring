package com.example.carrito.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetallePedido {

    @EmbeddedId
    private DetallePedidoId id;

    @JsonIgnore
    @MapsId("pedidoId")
    @ManyToOne
    private Pedido pedido;

    @JsonIgnore
    @MapsId("productoId")
    @ManyToOne
    private Producto producto;

    @Column
    private int cantidad;
}
