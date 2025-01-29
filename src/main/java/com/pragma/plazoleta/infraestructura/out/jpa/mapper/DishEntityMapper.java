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

    @Mapping(source = "dish.categoryId", target = "categoryId.id")
    @Mapping(source = "dish.restaurantId", target = "restaurantId.id")
    DishEntity toEntity(Dish dish);

    @Mapping(source = "restaurantId.id", target = "restaurantId")
    @Mapping(source = "categoryId.id", target = "categoryId")
    Dish toDish(DishEntity dishEntity);
}
