package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.RegisterRestaurantRequest;
import com.pragma.plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RegRestaurantRequestMapper {

    Restaurant toRestaurant(RegisterRestaurantRequest regRestaurantRequest);
}
