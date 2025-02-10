package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.DishDTO;
import com.pragma.plazoleta.application.dto.OrderStatusResponse;
import com.pragma.plazoleta.application.dto.OrdersStatusDTO;
import com.pragma.plazoleta.domain.model.AllOrderByStatusList;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.OrderDish;
import java.util.Collections;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderStatusResponseMapper {

//    @Mapping(source = "id", target = "order")
//    @Mapping(source = "client.id", target = "client")
//    @Mapping(source = "chef.id", target = "chef")
//    @Mapping(source = "orderDishes", target = "dishes", qualifiedByName = "mapOrderDishesToDishes")
//    OrderStatusResponse toResponse(Order order);

    @Mapping(source = "ordersList", target = "ordersList", qualifiedByName = "mapOrderStatusOrder")
    OrderStatusResponse toOrderStatusResponse(AllOrderByStatusList allOrderByStatusList);

    @Named("mapOrderStatusOrder")
    default  List<OrdersStatusDTO> mapOrderStatusOrder(List<Order> ordersList){
        if(ordersList == null){
            return Collections.emptyList();
        }
        return ordersList.stream()
                .map( order -> {
                    OrdersStatusDTO ordersStatusDTO = new OrdersStatusDTO();
                    ordersStatusDTO.setOrder(order.getId());
                    ordersStatusDTO.setClient(order.getClient().getId());
                    ordersStatusDTO.setStatus(order.getStatus());
                    ordersStatusDTO.setChef(order.getChef().getId());
                    ordersStatusDTO.setDate(order.getDate());
                    ordersStatusDTO.setDishes(order.getOrderDishes().stream().map(orderDish -> {
                        DishDTO dishDTO = new DishDTO();
                        dishDTO.setId(orderDish.getDishOrder().getId());
                        dishDTO.setName(orderDish.getDishOrder().getName());
                        dishDTO.setPrice(orderDish.getDishOrder().getPrice());
                        dishDTO.setUrlImage(orderDish.getDishOrder().getUrlImage());
                        dishDTO.setQuantity(orderDish.getQuantity());
                        return dishDTO;
                    }).toList());
                    return ordersStatusDTO;
                }).toList();
    }

}
