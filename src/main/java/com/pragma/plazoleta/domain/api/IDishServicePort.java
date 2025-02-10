package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.model.DishByCategoryList;
import com.pragma.plazoleta.domain.model.OrderDish;
import com.pragma.plazoleta.domain.model.Restaurant;


import java.util.List;

public interface IDishServicePort {
    void saveDish(Dish dish);

    void updateDish(Dish dish, Restaurant restaurant);

    Dish getDish(Long id);

    void validateDishAndEnable(List<OrderDish> orderDish);

    DishByCategoryList getByCategoryAllDish(Long restaurantId, Long categoryId,  int size, int page);

    void enableAndDisableDish(Dish dish, Restaurant restaurant, boolean status);
}
