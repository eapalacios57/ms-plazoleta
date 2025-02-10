package com.pragma.plazoleta.domain.model;

import com.pragma.plazoleta.domain.utils.StateOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order {

    private Long id;

    private UserDetail client;

    private String status;

    private UserDetail chef;

    private LocalDateTime date = LocalDateTime.now();

    private Restaurant restaurant;

    private List<OrderDish> orderDishes;

    private String pin;

    public void orderInPending(){
        this.status = StateOrder.PENDING.getStatus();
    }
}
