package com.pragma.plazoleta.infraestructura.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDishIdEntity {

    @Column(name = "id_order")
    private Long orderId;

    @Column(name = "id_dish")
    private Long dishId;
}
