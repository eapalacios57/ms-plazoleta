package com.pragma.plazoleta.infraestructura.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.RestaurantEntityMapper;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantEntityMapper.toRestaurant(restaurantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Restaurant not exists")));
    }

    @Override
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAllByOrderByNameAsc(pageable).map(restaurantEntityMapper::toRestaurant);
    }


}
