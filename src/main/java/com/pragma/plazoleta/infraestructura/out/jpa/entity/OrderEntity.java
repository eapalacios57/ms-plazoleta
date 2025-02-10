package com.pragma.plazoleta.infraestructura.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
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

    private String pin;
    @ManyToOne
    @JoinColumn(name="id_restaurant")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "orderDish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDishEntity> orderDishes = new ArrayList<>();
}
