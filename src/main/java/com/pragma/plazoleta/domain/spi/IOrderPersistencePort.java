package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.AllOrderByStatusList;
import com.pragma.plazoleta.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderPersistencePort {

    Order orderSave(Order order);

    Order getOrderLastByClient(Long client);

    AllOrderByStatusList getAllOrderByStatus(Long restaurantId, String status, int size, int page);

    Order getOrderById(Long orderId);
}
