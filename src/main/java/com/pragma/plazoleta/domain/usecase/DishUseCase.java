package com.pragma.plazoleta.domain.usecase;


import com.pragma.plazoleta.domain.model.DishByCategoryList;
import com.pragma.plazoleta.domain.utils.UserUtils;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.model.OrderDish;
import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import com.pragma.plazoleta.infraestructura.exception.ModifyDishException;
import lombok.RequiredArgsConstructor;


import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    @Override
    public void saveDish(Dish dish) {
        dish.setActive(true);
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public void updateDish(Dish dish, Restaurant restaurant ) {
        validateOwnerRestaurant(restaurant);
        dishPersistencePort.updateDish(dish);
    }

    @Override
    public void enableAndDisableDish(Dish dish, Restaurant restaurant, boolean status){
        validateOwnerRestaurant(restaurant);
        dish.setActive(status);
        dishPersistencePort.updateDish(dish);
    }

    @Override
    public Dish getDish(Long id) {
        return dishPersistencePort.getDish(id);
    }

    @Override
    public void validateDishAndEnable(List<OrderDish> orderDishList) {
        dishPersistencePort.validateDishAndEnable(orderDishList);
    }


    @Override
    public DishByCategoryList getByCategoryAllDish(Long restaurantId, Long categoryId, int size, int page) {
        return dishPersistencePort.getByCategoryAllDish(restaurantId, categoryId, size, page);
    }

    private void validateOwnerRestaurant(Restaurant restaurant) {
        Long currentUserId = UserUtils.getCurrentUser();
        if(!Objects.equals(currentUserId, restaurant.getOwnerId())){
            throw new ModifyDishException();
        }
    }
}
