package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RegisterDishRequest;
import com.pragma.plazoleta.domain.model.Dish;
import org.springframework.data.domain.Page;

public interface IDishHandler {
    void saveDish(RegisterDishRequest request) throws IllegalAccessException;

    void updateDish(RegisterDishRequest request, Long id) throws IllegalAccessException;

    void disabledDish(Long dishId) throws IllegalAccessException;

    void enabledDish(Long dishId) throws IllegalAccessException;

    Page<Dish> getAllDishesByCategory(Long categoryId, int page, int size);
}
