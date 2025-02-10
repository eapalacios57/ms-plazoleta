package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.RegisterRestaurantRequest;
import com.pragma.plazoleta.application.dto.RestaurantsResponse;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.application.mapper.RegRestaurantRequestMapper;
import com.pragma.plazoleta.application.mapper.RestaurantsAllResponseMapper;
import com.pragma.plazoleta.domain.api.IApiUserServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.model.UserDetail;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final RegRestaurantRequestMapper restaurantRequestMapper;
    private final RestaurantsAllResponseMapper restaurantsAllResponseMapper;
    private final IApiUserServicePort apiUserServicePort;

    @Override
    public void saveRestaurant(RegisterRestaurantRequest regRestaurantRequest) {
        UserDetail userDetail = apiUserServicePort.getUserDetail(regRestaurantRequest.getOwnerId());
        restaurantServicePort.validateUserOwner(userDetail);
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(regRestaurantRequest));
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantServicePort.getRestaurant(id);
    }

    @Override
    public RestaurantsResponse getAllRestaurants(int page, int size ) {
        return  restaurantsAllResponseMapper.toRestaurant(restaurantServicePort.getAllRestaurants(size, page));
    }


}
