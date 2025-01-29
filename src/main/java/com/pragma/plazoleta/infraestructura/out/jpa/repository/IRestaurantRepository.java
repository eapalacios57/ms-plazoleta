package com.pragma.plazoleta.infraestructura.out.jpa.repository;

import com.pragma.plazoleta.infraestructura.out.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    Optional<RestaurantEntity> findByNit(String nit);

    Page<RestaurantEntity> findAllByOrderByNameAsc(Pageable pageable);
}
