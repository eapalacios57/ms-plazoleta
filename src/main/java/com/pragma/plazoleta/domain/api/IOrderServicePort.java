package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.AllOrderByStatusList;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.OrderDish;
import java.util.List;

public interface IOrderServicePort {
    Order saveOrder(Order order);

    void saveOrderDish(List<OrderDish> orderDishList);

    void validateLastOrder();

    AllOrderByStatusList getAllOrderByStatus(Long restaurantId, String status, int size, int page);

    void updateAssignOrder(Order order);

    void orderAssignClient(Order order);

    Order getOrderById(Long orderId);

    Order changeState(Order order, String stateNew, String pin);


}
