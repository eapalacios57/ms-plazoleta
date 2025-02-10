package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.OrderDish;

import java.util.List;

public interface IOrderDishPersistencePort {

    void saveOrderDish(List<OrderDish> orderDishList);

}
