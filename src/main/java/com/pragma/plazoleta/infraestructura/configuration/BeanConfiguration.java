package com.pragma.plazoleta.infraestructura.configuration;


import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.usecase.DishUseCase;
import com.pragma.plazoleta.domain.usecase.RestaurantUseCase;
import com.pragma.plazoleta.infraestructura.out.jpa.adapter.DishJpaAdapter;
import com.pragma.plazoleta.infraestructura.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.DishEntityMapper;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.RestaurantEntityMapper;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IDishRepository;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
