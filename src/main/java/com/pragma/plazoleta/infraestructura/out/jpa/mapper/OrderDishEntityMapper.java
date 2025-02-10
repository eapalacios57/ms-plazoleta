package com.pragma.plazoleta.infraestructura.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.OrderDish;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderDishEntityMapper {

    @Mapping(source="order.id", target="id.orderId")
    @Mapping(source = "dishOrder.id", target = "id.dishId")
    @Mapping(source = "dishOrder.categoryId.id", target = "dishOrder.categoryId.id")
    @Mapping(source = "dishOrder.restaurantId.id", target = "dishOrder.restaurantId.id")
    OrderDishEntity toEntity(OrderDish orderDish);

    List<OrderDishEntity> toEntityList(List<OrderDish> orderDishList);

}
