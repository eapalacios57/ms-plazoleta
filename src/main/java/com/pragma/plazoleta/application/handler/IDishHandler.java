package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RegisterDishRequest;
import com.pragma.plazoleta.application.dto.UpdateDishRequest;
import com.pragma.plazoleta.application.dto.DishByCategoryResponse;

public interface IDishHandler {
    void saveDish(RegisterDishRequest request) throws IllegalAccessException;

    void updateDish(UpdateDishRequest request, Long id) throws IllegalAccessException;

    void disabledDish(Long dishId) throws IllegalAccessException;

    void enabledDish(Long dishId) throws IllegalAccessException;

    DishByCategoryResponse getAllDishesByCategory(Long restaurantId, Long categoryId, int page, int size);
}
