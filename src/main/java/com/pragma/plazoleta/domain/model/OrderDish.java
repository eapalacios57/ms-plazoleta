package com.pragma.plazoleta.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDish {
    private Order order;

    private Dish dishOrder;

    private Integer quantity;
}
