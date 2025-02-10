package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.model.DishByCategoryList;
import com.pragma.plazoleta.domain.model.OrderDish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IDishPersistencePort {

    void saveDish(Dish dish);

    void updateDish(Dish dish);

    Dish getDish(Long id);

    DishByCategoryList getByCategoryAllDish(Long restaurantId, Long categoryId, int size, int page);

    void validateDishAndEnable(List<OrderDish> orderDishList);
}
