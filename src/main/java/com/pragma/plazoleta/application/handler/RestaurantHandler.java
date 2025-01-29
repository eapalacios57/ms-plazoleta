package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RegisterRestaurantRequest;
import com.pragma.plazoleta.application.dto.RestaurantsResponse;
import com.pragma.plazoleta.application.mapper.RegRestaurantRequestMapper;
import com.pragma.plazoleta.application.mapper.RestaurantsAllResponseMapper;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.Restaurant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final RegRestaurantRequestMapper restaurantRequestMapper;
    private final RestaurantsAllResponseMapper restaurantsAllResponseMapper;

    @Override
    public void saveRestaurant(RegisterRestaurantRequest regRestaurantRequest) throws IllegalAccessException {
        validateRestaurantRequest(regRestaurantRequest);
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(regRestaurantRequest));
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantServicePort.getRestaurant(id);
    }

    @Override
    public Page<RestaurantsResponse> getAllRestaurants(int page, int size ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Restaurant> restaurantesPage = restaurantServicePort.getAllRestaurants(pageable);
        return restaurantesPage.map(restaurantsAllResponseMapper::toRestaurant);
    }

    public  void validateRestaurantRequest(RegisterRestaurantRequest request) throws IllegalAccessException {

        if (request.getNit() == null || !request.getNit().matches("\\d+")) {
            throw new IllegalAccessException("NIT must be numeric.");
        }

        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalAccessException("The name cannot be blank.");
        }
        if (request.getName().matches("\\d+")) {
             throw new IllegalAccessException("The name cannot contain only numbers.");
        }

        if (request.getAddress() == null || request.getAddress().isBlank()) {
             throw new IllegalAccessException("Address is required.");
        }
        if (request.getPhone() == null || !request.getPhone().matches("^\\+?\\d{1,13}$")) {
             throw new IllegalAccessException("Phone must be numeric and can include '+'.");
        }
        if (request.getUrlLogo() == null || request.getUrlLogo().isBlank()) {
             throw new IllegalAccessException("UrlLogo is required.");
        }
        if (request.getOwnerId() == null) {
             throw new IllegalAccessException("User ID is required.");
        }
    }
}
