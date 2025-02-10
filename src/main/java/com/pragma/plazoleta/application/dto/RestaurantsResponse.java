package com.pragma.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantsResponse {

    List<RestaurantDTO> listRestaurants;
    int size;
    int page;
    long totalElements;
    int totalPage;

}
