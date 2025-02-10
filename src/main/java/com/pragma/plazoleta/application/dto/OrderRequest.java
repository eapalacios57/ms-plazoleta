package com.pragma.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private Long restaurantId;
    private List<DishOrderDTO> items;

}
