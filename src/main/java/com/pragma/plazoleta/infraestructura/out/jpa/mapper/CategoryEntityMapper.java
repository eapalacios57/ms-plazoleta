package com.pragma.plazoleta.infraestructura.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.Category;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {

    Category toCategory(CategoryEntity categoryEntity);
}
