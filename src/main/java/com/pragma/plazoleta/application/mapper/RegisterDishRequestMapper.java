package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.RegisterDishRequest;
import com.pragma.plazoleta.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RegisterDishRequestMapper {

    @Mapping(source="restaurantId", target = "restaurantId.id")
    @Mapping(source="categoryId", target = "categoryId.id")
    Dish toDish(RegisterDishRequest regDishRequest);

}
