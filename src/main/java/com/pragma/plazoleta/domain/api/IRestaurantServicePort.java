package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.RestaurantList;
import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.model.UserDetail;

public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(Long id);

    RestaurantList getAllRestaurants(int size, int page);

    void validateUserOwner(UserDetail userDetail);

    void validateRestaurant(String nit);

}
