package com.pragma.plazoleta.infraestructura.out.jpa.adapter;


import com.pragma.plazoleta.domain.model.AllOrderByStatusList;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.spi.IOrderPersistencePort;
import com.pragma.plazoleta.infraestructura.exception.NotFoundException;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.OrderEntityMapper;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public Order orderSave(Order order) {
        return orderEntityMapper.toOrder(orderRepository.save(orderEntityMapper.toEntity(order)));
    }

    @Override
    public Order getOrderLastByClient(Long client) {
        return orderEntityMapper.toOrder(orderRepository.findByClientOrderByIdDesc(client));
    }

    @Override
    public AllOrderByStatusList getAllOrderByStatus(Long restaurantId, String status, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orderPage =  orderRepository.findByRestaurantIdAndStatus(restaurantId, status, pageable).map(orderEntityMapper::toOrder);
        return new AllOrderByStatusList(orderPage.getContent(), size, page, orderPage.getTotalElements(), orderPage.getTotalPages());
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderEntityMapper.toOrder(orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order Not Found.")));
    }

}
