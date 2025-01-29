package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RegisterDishRequest;
import com.pragma.plazoleta.application.dto.UserDetailResponse;
import com.pragma.plazoleta.application.mapper.RegisterDishRequestMapper;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.model.Restaurant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final RegisterDishRequestMapper dishRequestMapper;
    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public void saveDish(RegisterDishRequest request) throws IllegalAccessException {
        validateDishRequest(request);
        Dish dish = dishRequestMapper.toDish(request);
        dish.setActive(true);
        dishServicePort.saveDish(dish);
    }

    @Override
    public void updateDish(RegisterDishRequest request, Long id) throws IllegalAccessException {
          if(request.getPrice() < 0){
                throw new IllegalAccessException("The price of the dish must be greater than zero");
          }
          Dish dishUpdate = dishServicePort.getDish(id);
          dishUpdate.setPrice(request.getPrice());
          dishUpdate.setDescription(request.getDescription());
          dishServicePort.updateDish(dishUpdate);
    }

    @Override
    public void disabledDish(Long dishId) throws IllegalAccessException {
        Dish dishUpdate = dishServicePort.getDish(dishId);
        validateOwnerRestaurant(dishUpdate);
        dishUpdate.setActive(false);
        dishServicePort.updateDish(dishUpdate);
    }

    @Override
    public void enabledDish(Long dishId) throws IllegalAccessException {
        Dish dishUpdate = dishServicePort.getDish(dishId);
        validateOwnerRestaurant(dishUpdate);
        dishUpdate.setActive(true);
        dishServicePort.updateDish(dishUpdate);
    }

    @Override
    public Page<Dish> getAllDishesByCategory(Long categoryId, int page, int size)  {
        Pageable pageable = PageRequest.of(page, size);
        return dishServicePort.getByCategoryAllDish(categoryId, pageable);
    }


    private void validateDishRequest(RegisterDishRequest request) throws IllegalAccessException {
        if(request.getPrice() < 0){
            throw new IllegalAccessException("The price of the dish must be greater than zero");
        }
        if(request.getDescription() == null || request.getRestaurantId() == null || request.getCategoryId() == null
            || request.getName() == null || request.getUrlImage() == null ){
            throw new IllegalAccessException("All fields are required.");
        }
    }

    private void validateOwnerRestaurant(Dish dish) throws IllegalAccessException {
        Restaurant restaurant = restaurantServicePort.getRestaurant(dish.getRestaurantId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailResponse userDetail = (UserDetailResponse) authentication.getPrincipal();
        if(!Objects.equals(userDetail.getId(), restaurant.getOwnerId())){
            throw new IllegalAccessException("You cannot modify the dish.");
        }
    }
}
