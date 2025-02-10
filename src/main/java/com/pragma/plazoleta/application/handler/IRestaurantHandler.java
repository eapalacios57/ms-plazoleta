package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RegisterRestaurantRequest;
import com.pragma.plazoleta.application.dto.RestaurantsResponse;
import com.pragma.plazoleta.domain.model.Restaurant;



public interface IRestaurantHandler {

    void  saveRestaurant(RegisterRestaurantRequest request) throws IllegalAccessException;
    Restaurant getRestaurant(Long id);

   RestaurantsResponse getAllRestaurants(int page, int size);
}
