package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.OrderRequest;
import com.pragma.plazoleta.application.dto.OrderStatusResponse;
import com.pragma.plazoleta.application.handler.IOrderHandler;
import com.pragma.plazoleta.application.mapper.CreateOrderRequestMapper;
import com.pragma.plazoleta.application.mapper.MessageMapper;
import com.pragma.plazoleta.application.mapper.OrderStatusResponseMapper;
import com.pragma.plazoleta.domain.utils.UserUtils;
import com.pragma.plazoleta.domain.api.*;
import com.pragma.plazoleta.domain.model.Message;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.OrderDish;
import com.pragma.plazoleta.domain.model.UserDetail;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final CreateOrderRequestMapper createOrderRequestMapper;
    private final IOrderServicePort orderServicePort;
    private final OrderStatusResponseMapper orderStatusResponseMapper;
    private final IApiUserServicePort apiUserServicePort;
    private final ITraceabilityServicePort traceabilityServicePort;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = createOrderRequestMapper.toOrder(orderRequest);
        Order orderSave = orderServicePort.saveOrder(order);
        List<OrderDish> orderDishList = createOrderRequestMapper.toOrderDishes(orderRequest.getItems(), orderSave.getId());
        orderServicePort.saveOrderDish(orderDishList);
    }

    @Override
    public OrderStatusResponse getOrderStatus(String status, int page, int size) {
        UserDetail userDetail = apiUserServicePort.getUserDetail(UserUtils.getCurrentUser());
        return  orderStatusResponseMapper.toOrderStatusResponse(orderServicePort.getAllOrderByStatus(userDetail.getRestaurantId(), status, size, page));

    }

    @Override
    public void updateOrderAssign(Long orderId) {
       Order orderUpdate = orderServicePort.getOrderById(orderId);
       orderServicePort.updateAssignOrder(orderUpdate);
    }

    @Override
    public void updateOrderStatus(Long orderId, String status, String pin) {
        Order order = orderServicePort.getOrderById(orderId);
        String statusOld = order.getStatus();
        Order orderUpdate = orderServicePort.changeState(order, status, pin);
        traceabilityServicePort.modelSendTraceabilityLogs(orderUpdate, statusOld);
    }


}
