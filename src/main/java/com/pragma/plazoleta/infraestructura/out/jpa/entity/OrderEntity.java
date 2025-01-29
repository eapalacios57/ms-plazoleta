package com.pragma.plazoleta.infraestructura.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_client")
    private Long client;

    private LocalDateTime date;

    private String status;

    @Column(name="id_chef")
    private Long chef;

    @ManyToOne
    @JoinColumn(name="id_restaurant")
    private RestaurantEntity restaurant;
}
