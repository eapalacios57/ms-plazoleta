package com.pragma.plazoleta.infraestructura.out.jpa.mapper;


import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.CategoryEntity;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

    @Mapping(source = "restaurant.id", target = "restaurant.id")
    @Mapping(source = "client.id", target = "client")
    @Mapping(source = "chef.id", target = "chef")
    @Mapping(target = "orderDishes", ignore = true)
    OrderEntity toEntity(Order order);


    @Mapping(source = "restaurant.id", target = "restaurant.id")
    @Mapping(source = "client", target = "client.id")
    @Mapping(source = "chef", target = "chef.id")
    Order toOrder(OrderEntity orderEntity);

}
