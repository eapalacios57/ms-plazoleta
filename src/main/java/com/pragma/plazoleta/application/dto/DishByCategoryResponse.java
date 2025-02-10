package com.pragma.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishByCategoryResponse {

    List<DishByCategoryDTO> dishList;
    int size;
    int page;
    long totalElements;
    int totalPage;

}
