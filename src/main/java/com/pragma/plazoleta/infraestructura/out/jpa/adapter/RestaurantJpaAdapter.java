package com.pragma.plazoleta.infraestructura.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.RestaurantList;
import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.infraestructura.exception.NotFoundException;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.RestaurantEntityMapper;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return restaurantEntityMapper.toRestaurant(restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant Not Found.")));
    }

    @Override
    public RestaurantList getAllRestaurants(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Restaurant> pageRestaurant = restaurantRepository.findAllByOrderByNameAsc(pageable).map(restaurantEntityMapper::toRestaurant);
        return new RestaurantList(pageRestaurant.getContent(), size, page, pageRestaurant.getTotalElements(), pageRestaurant.getTotalPages());
    }

    @Override
    public void validateRestaurant(String nit) {
      if(restaurantRepository.findByNit(nit).isPresent()){
         throw new NotFoundException("Restaurant already exists");
      }
    }


}
