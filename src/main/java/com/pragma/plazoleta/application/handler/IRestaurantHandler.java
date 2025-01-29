package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RegisterRestaurantRequest;
import com.pragma.plazoleta.application.dto.RestaurantsResponse;
import com.pragma.plazoleta.domain.model.Restaurant;
import org.springframework.data.domain.Page;


public interface IRestaurantHandler {

    void  saveRestaurant(RegisterRestaurantRequest request) throws IllegalAccessException;
    Restaurant getRestaurant(Long id);

    Page<RestaurantsResponse> getAllRestaurants(int page, int size);
}
