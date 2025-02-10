package com.pragma.plazoleta.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dish {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private String urlImage;
    private Restaurant restaurantId;
    private Category categoryId;
    private Boolean active;

}
