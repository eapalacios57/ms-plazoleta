package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.RestaurantList;
import com.pragma.plazoleta.domain.model.Restaurant;


public interface IRestaurantPersistencePort {

    void saveRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(Long id);

    public RestaurantList getAllRestaurants(int size, int page);

    void validateRestaurant(String nit);

}
