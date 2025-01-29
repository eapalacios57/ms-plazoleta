package com.pragma.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDishRequest {

    private String name;

    private String description;

    private Long price;

    private Long categoryId;

    private String urlImage;

    private String restaurantId;

}
