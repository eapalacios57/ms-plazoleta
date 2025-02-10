package com.pragma.plazoleta.infraestructura.out.jpa.repository;

import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.infraestructura.out.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity findByClientOrderByIdDesc(Long client);

    Page<OrderEntity> findByRestaurantIdAndStatus(Long restaurantId, String status,  Pageable pageable);


}
