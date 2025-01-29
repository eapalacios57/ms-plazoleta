package com.pragma.plazoleta.infraestructura.out.jpa.repository;

import com.pragma.plazoleta.infraestructura.out.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    Page<DishEntity> findByCategoryIdIdAndActive(Long categoryId, Boolean active, Pageable pageable);



}
