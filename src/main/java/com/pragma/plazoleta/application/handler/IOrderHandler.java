package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.OrderRequest;
import com.pragma.plazoleta.application.dto.OrderStatusResponse;

public interface IOrderHandler {
    public void createOrder(OrderRequest orderRequest);

    OrderStatusResponse getOrderStatus(String status, int page, int size);

    void updateOrderAssign(Long orderId);

    void updateOrderStatus(Long orderId, String status, String pin);
}
