package com.pragma.plazoleta.infraestructura.out.jpa.repository;

import com.pragma.plazoleta.infraestructura.out.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    Page<DishEntity> findByRestaurantIdIdAndCategoryIdIdAndActive(Long restaurantId, Long categoryId, Boolean active, Pageable pageable);
    Optional<DishEntity> findByIdAndActive(Long dishId, boolean active);


}
