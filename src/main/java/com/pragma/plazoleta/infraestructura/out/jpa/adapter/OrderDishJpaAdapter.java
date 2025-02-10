package com.pragma.plazoleta.infraestructura.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.OrderDish;
import com.pragma.plazoleta.domain.spi.IOrderDishPersistencePort;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.OrderDishEntityMapper;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IDishRepository;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IOrderDishRepository;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IOrderRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort {
    private final IOrderDishRepository orderDishRepository;
    private final OrderDishEntityMapper orderDishEntityMapper;

    @Override
    public void saveOrderDish(List<OrderDish> orderDishList) {

        orderDishRepository.saveAll(orderDishEntityMapper.toEntityList(orderDishList));
    }


}
