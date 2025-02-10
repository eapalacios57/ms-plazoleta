package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.DishByCategoryResponse;
import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.model.DishByCategoryList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishByCategoryMapper {
    DishByCategoryResponse toDishByCategoryResponse(DishByCategoryList dishList);
}
