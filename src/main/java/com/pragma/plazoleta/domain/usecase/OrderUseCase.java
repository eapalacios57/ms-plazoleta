package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.*;
import com.pragma.plazoleta.domain.model.*;
import com.pragma.plazoleta.domain.utils.Constants;
import com.pragma.plazoleta.domain.utils.StateOrder;
import com.pragma.plazoleta.domain.utils.UserUtils;
import com.pragma.plazoleta.domain.spi.IOrderDishPersistencePort;
import com.pragma.plazoleta.domain.spi.IOrderPersistencePort;
import com.pragma.plazoleta.infraestructura.exception.NotFoundException;
import com.pragma.plazoleta.infraestructura.exception.OrderInProcessException;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderDishPersistencePort orderDishPersistencePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishServicePort dishServicePort;
    private final INotificationServicePort notificationServicePort;

    @Override
    public Order saveOrder(Order order){
        this.validateLastOrder();
        restaurantServicePort.getRestaurant(order.getRestaurant().getId());
        order.orderInPending();
        this.orderAssignClient(order);
        return orderPersistencePort.orderSave(order);
    }

    @Override
    public void saveOrderDish(List<OrderDish> orderDishList) {
        dishServicePort.validateDishAndEnable(orderDishList);
        orderDishPersistencePort.saveOrderDish(orderDishList);
    }

    public void validateLastOrder(){
        Long client = UserUtils.getCurrentUser();
        Order orderLastClient = orderPersistencePort.getOrderLastByClient(client);
        if(orderLastClient != null){
            String statusOrder = orderLastClient.getStatus();
            if(statusOrder.equals(StateOrder.PENDING.getStatus()) || statusOrder.equals(StateOrder.PREPARATION.getStatus()) || statusOrder.equals(StateOrder.READY.getStatus())) {
                throw new OrderInProcessException("You currently have an order in the following status " + statusOrder);
            }
        }
    }

    @Override
    public AllOrderByStatusList getAllOrderByStatus(Long restaurantId, String status, int size, int page) {
        return orderPersistencePort.getAllOrderByStatus( restaurantId, status, size, page);
    }

    @Override
    public void updateAssignOrder(Order order) {
        order.setStatus(StateOrder.PREPARATION.getStatus());
        UserDetail userDetail = new UserDetail();
        userDetail.setId(UserUtils.getCurrentUser());
        order.setChef(userDetail);
        orderPersistencePort.orderSave(order);
    }


    @Override
    public void orderAssignClient(Order order) {
        Long userId = UserUtils.getCurrentUser();
        UserDetail userDetail = new UserDetail();
        userDetail.setId(userId);
        order.setClient(userDetail);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderPersistencePort.getOrderById(orderId);
    }

    @Override
    public Order changeState(Order order, String stateNew, String pin) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        switch (stateNew) {
            case Constants.PREPARATION:
                updateAssignOrder(order);
                return order;

            case Constants.READY:
                if (!order.getStatus().equals(Constants.PREPARATION)) {
                    throw new NotFoundException("Only orders in 'Preparation' status can be moved to 'Ready'.");
                }
                order.setStatus(StateOrder.READY.getStatus());
                order.setPin(generatePin());
                Order orderUpdate = orderPersistencePort.orderSave(order);
                notificationServicePort.notifyClient(orderUpdate);
                return orderUpdate;

            case Constants.DELIVERED:
                changeOrderDelivered(order, pin);
                return orderPersistencePort.orderSave(order);

            case Constants.CANCELED:
                if (!order.getStatus().equals(Constants.PENDING)) {
                    throw new NotFoundException("Lo sentimos, tu pedido ya está en preparación y no puede cancelarse.");
                }
                order.setStatus(Constants.CANCELED);
                return orderPersistencePort.orderSave(order);

            default:
                throw new IllegalArgumentException("Invalid order state: " + stateNew);
        }
    }

    public void changeOrderDelivered(Order order, String pin){
        if(!order.getStatus().equals(StateOrder.READY.getStatus())){
            throw new NotFoundException("Only orders in the 'Ready' status can be moved to 'Delivered'.");
        }
        if (pin == null || pin.isEmpty()) {
            throw new NotFoundException("Se requiere un pin de seguridad para marcar el pedido como 'DELIVERED'");
        }
        if(!pin.equals(order.getPin())){
            throw new NotFoundException("El pin digitado no corresponde al enviado.");
        }
        order.setStatus(StateOrder.DELIVERED.getStatus());
        orderPersistencePort.orderSave(order);
    }


    public String generatePin(){
        Random random = new Random();
        int pin = random.nextInt(900000) + 100000;
        return  String.valueOf(pin);
    }

}
