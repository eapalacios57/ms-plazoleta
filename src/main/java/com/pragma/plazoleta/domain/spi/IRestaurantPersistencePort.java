package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IRestaurantPersistencePort {

    void saveRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(Long id);

    public Page<Restaurant> getAllRestaurants(Pageable pageable);

}
