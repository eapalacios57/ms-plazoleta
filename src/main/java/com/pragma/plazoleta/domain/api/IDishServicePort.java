package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishServicePort {
    void saveDish(Dish dish);

    void updateDish(Dish dish);

    Dish getDish(Long id);

    Page<Dish> getByCategoryAllDish(Long categoryId, Pageable pageable);
}
