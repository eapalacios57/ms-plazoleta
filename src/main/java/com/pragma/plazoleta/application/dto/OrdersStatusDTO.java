package com.pragma.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrdersStatusDTO {

    private Long order;

    private Long client;

    private String status;

    private Long chef;

    private LocalDateTime date;

    private List<DishDTO> dishes;
}
