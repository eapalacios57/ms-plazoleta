package com.pragma.plazoleta.infraestructura.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurant);

    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

    List<Restaurant> toListRestaurants(List<Restaurant> restaurants);
}
