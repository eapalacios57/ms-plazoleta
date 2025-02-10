package com.pragma.plazoleta.infraestructura.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.model.DishByCategoryList;
import com.pragma.plazoleta.domain.model.OrderDish;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import com.pragma.plazoleta.infraestructura.exception.NotFoundException;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.DishEntityMapper;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    public DishByCategoryList getByCategoryAllDish(Long restaurantId, Long categoryId, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Dish> pageDish = dishRepository.findByRestaurantIdIdAndCategoryIdIdAndActive(restaurantId, categoryId,true, pageable).map(dishEntityMapper::toDish);
        return new DishByCategoryList(pageDish.getContent(), size, page, pageDish.getTotalElements(), pageDish.getTotalPages());
    }

    @Override
    public void validateDishAndEnable(List<OrderDish> orderDishList) {
        orderDishList.forEach(dish -> {
            if(dishRepository.findByIdAndActive(dish.getDishOrder().getId(), true).isEmpty()){
                throw new NotFoundException("The dish with ID " + dish.getDishOrder().getId() + " is not active or does not exist.");
            }
        });
    }
}
