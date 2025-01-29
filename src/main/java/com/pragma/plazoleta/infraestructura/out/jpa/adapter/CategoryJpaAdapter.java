package com.pragma.plazoleta.infraestructura.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.Category;
import com.pragma.plazoleta.domain.spi.ICategoryPersistencePort;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.CategoryEntityMapper;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private ICategoryRepository categoryRepository;
    private CategoryEntityMapper categoryEntityMapper;

    @Override
    public Category getCategory(Long id) {
        return categoryEntityMapper.toCategory(categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category Not Found.")));
    }
}
