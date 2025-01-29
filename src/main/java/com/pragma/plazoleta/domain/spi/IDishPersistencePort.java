package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IDishPersistencePort {

    void saveDish(Dish dish);

    void updateDish(Dish dish);

    Dish getDish(Long id);

    Page<Dish> getByCategoryAllDish(Long categoryId, Pageable pageable);
}
