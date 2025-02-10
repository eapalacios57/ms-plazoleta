package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.RestaurantList;
import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.model.UserDetail;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.infraestructura.exception.NotFoundException;


public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        this.validateRestaurant(restaurant.getNit());
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public RestaurantList getAllRestaurants(int size, int page) {
        return restaurantPersistencePort.getAllRestaurants(size, page);
    }

    @Override
    public void validateUserOwner(UserDetail userDetail) {
        if(!userDetail.getRole().equals("OWNER")){
            throw new NotFoundException("he user does not have the Owner role.");
        }
    }

    @Override
    public void validateRestaurant(String nit){
        restaurantPersistencePort.validateRestaurant(nit);
    }

}
