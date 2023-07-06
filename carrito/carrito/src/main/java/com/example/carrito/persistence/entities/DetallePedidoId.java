package com.example.carrito.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class DetallePedidoId implements Serializable {

    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Column(name = "producto_id")
    private Integer productoId;

}
