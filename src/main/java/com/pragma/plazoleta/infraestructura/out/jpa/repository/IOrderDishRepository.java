package com.pragma.plazoleta.infraestructura.out.jpa.repository;

import com.pragma.plazoleta.infraestructura.out.jpa.entity.OrderDishEntity;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.OrderDishIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity, OrderDishIdEntity> {
}
