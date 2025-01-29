package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(Long id);

    Page<Restaurant> getAllRestaurants(Pageable pageable);

}
