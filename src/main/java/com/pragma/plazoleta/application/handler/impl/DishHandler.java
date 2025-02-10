package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.RegisterDishRequest;
import com.pragma.plazoleta.application.dto.UpdateDishRequest;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.mapper.DishByCategoryMapper;
import com.pragma.plazoleta.application.dto.DishByCategoryResponse;
import com.pragma.plazoleta.application.mapper.RegisterDishRequestMapper;
import com.pragma.plazoleta.domain.api.ICategoryServicePort;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.model.Restaurant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final RegisterDishRequestMapper dishRequestMapper;
    private final IRestaurantServicePort restaurantServicePort;
    private final ICategoryServicePort categoryServicePort;
    private final DishByCategoryMapper dishByCategoryMapper;

    @Override
    public void saveDish(RegisterDishRequest request) {
        Dish dish = dishRequestMapper.toDish(request);
        restaurantServicePort.getRestaurant(dish.getRestaurantId().getId());
        categoryServicePort.getCategory(dish.getCategoryId().getId());
        dishServicePort.saveDish(dish);
    }

    @Override
    public void updateDish(UpdateDishRequest request, Long id)  {
          Dish dishUpdate = dishServicePort.getDish(id);
          dishUpdate.setPrice(request.getPrice());
          dishUpdate.setDescription(request.getDescription());
          Restaurant restaurant = restaurantServicePort.getRestaurant(dishUpdate.getRestaurantId().getId());
          dishServicePort.updateDish(dishUpdate, restaurant);
    }

    @Override
    public void disabledDish(Long dishId) throws IllegalAccessException {
        Dish dishUpdate = dishServicePort.getDish(dishId);
        Restaurant restaurant = restaurantServicePort.getRestaurant(dishUpdate.getRestaurantId().getId());
        dishServicePort.enableAndDisableDish(dishUpdate, restaurant, false);
    }

    @Override
    public void enabledDish(Long dishId) throws IllegalAccessException {
        Dish dishUpdate = dishServicePort.getDish(dishId);
        Restaurant restaurant = restaurantServicePort.getRestaurant(dishUpdate.getRestaurantId().getId());
        dishServicePort.enableAndDisableDish(dishUpdate, restaurant, true);
    }

    @Override
    public DishByCategoryResponse getAllDishesByCategory(Long restaurantId, Long categoryId, int page, int size)  {

        return dishByCategoryMapper.toDishByCategoryResponse(dishServicePort.getByCategoryAllDish(restaurantId, categoryId, size, page));

    }


}
