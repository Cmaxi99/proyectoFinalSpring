package com.example.carrito.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_de_pedido")
    private Integer numeroPedido;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detallePedidos;

    @Column(name = "precio_total")
    private float total;
}
