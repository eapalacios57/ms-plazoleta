package com.pragma.plazoleta.infraestructura.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.DishEntityMapper;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class DishJpaAdapter  implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

    @Override
    public void saveDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public void updateDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public Dish getDish(Long id) {
        return dishRepository.findById(id).map(dishEntityMapper::toDish).orElseThrow(() -> new IllegalArgumentException("Dish not found."));
    }

    @Override
    public Page<Dish> getByCategoryAllDish(Long categoryId, Pageable pageable) {
        return dishRepository.findByCategoryIdIdAndActive(categoryId,true, pageable).map(dishEntityMapper::toDish);
    }
}
