package com.pragma.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishByCategoryDTO {
    private String id;
    private String name;
    private String description;
    private Long price;
    private String urlImage;
}
