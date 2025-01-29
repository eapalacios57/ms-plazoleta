package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.RestaurantsResponse;
import com.pragma.plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantsAllResponseMapper {
    RestaurantsResponse toRestaurant(Restaurant restaurant);
    List<RestaurantsResponse> toListRestaurants(List<Restaurant> restaurants);
}
