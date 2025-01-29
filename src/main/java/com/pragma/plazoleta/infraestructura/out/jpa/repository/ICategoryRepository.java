package com.pragma.plazoleta.infraestructura.out.jpa.repository;

import com.pragma.plazoleta.infraestructura.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
