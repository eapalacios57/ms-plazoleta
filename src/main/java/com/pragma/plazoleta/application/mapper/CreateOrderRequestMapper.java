package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.DishOrderDTO;
import com.pragma.plazoleta.application.dto.OrderRequest;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CreateOrderRequestMapper {

    @Mapping(source="restaurantId", target = "restaurant.id")
    Order toOrder(OrderRequest orderRequest);

    @Mapping(source = "dishOrderDTO.dishId", target = "dishOrder.id")
    @Mapping(source = "dishOrderDTO.quantity", target = "quantity")
    @Mapping(source = "orderId", target = "order.id")
    OrderDish toOrderDish(DishOrderDTO dishOrderDTO, Long orderId);

    default List<OrderDish> toOrderDishes(List<DishOrderDTO> items, Long orderId) {
        return items.stream()
                .map(item -> toOrderDish(item, orderId))
                .toList();
    }

}
