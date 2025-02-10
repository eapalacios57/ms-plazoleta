package com.pragma.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishDTO {

    private Long id;
    private String name;
    private Long price;
    private String urlImage;
    private Integer quantity;
}
