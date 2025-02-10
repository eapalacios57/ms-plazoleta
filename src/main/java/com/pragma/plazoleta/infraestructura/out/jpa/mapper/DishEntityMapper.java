package com.pragma.plazoleta.infraestructura.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishEntityMapper {

    @Mapping(source = "dish.categoryId.id", target = "categoryId.id")
    @Mapping(source = "dish.restaurantId.id", target = "restaurantId.id")
    DishEntity toEntity(Dish dish);

    @Mapping(source = "restaurantId.id", target = "restaurantId.id")
    @Mapping(source = "categoryId.id", target = "categoryId.id")
    Dish toDish(DishEntity dishEntity);
}
