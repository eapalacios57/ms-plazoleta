package com.pragma.plazoleta.infraestructura.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDishEntity {

    @EmbeddedId
    private  OrderDishIdEntity id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private OrderEntity orderDish;

    @ManyToOne
    @JoinColumn(name = "id_dish", insertable = false, updatable = false)
    private DishEntity dishOrder;

}
