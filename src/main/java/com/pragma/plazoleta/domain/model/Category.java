package com.pragma.plazoleta.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Category {

    private Long id;

    private  String name;

    private List<Dish> dish;
}
