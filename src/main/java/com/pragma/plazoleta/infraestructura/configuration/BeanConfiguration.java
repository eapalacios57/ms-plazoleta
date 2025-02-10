package com.pragma.plazoleta.infraestructura.configuration;


import com.pragma.plazoleta.domain.api.*;
import com.pragma.plazoleta.domain.model.Traceablility;
import com.pragma.plazoleta.domain.spi.*;
import com.pragma.plazoleta.domain.usecase.*;
import com.pragma.plazoleta.infraestructura.out.feign.adapter.TraceabilityApiAdapter;
import com.pragma.plazoleta.infraestructura.out.feign.rest.MessageFeignClient;
import com.pragma.plazoleta.infraestructura.out.feign.rest.UserFeignClient;
import com.pragma.plazoleta.infraestructura.out.jpa.adapter.*;
import com.pragma.plazoleta.infraestructura.out.jpa.mapper.*;
import com.pragma.plazoleta.infraestructura.out.jpa.repository.*;
import com.pragma.plazoleta.infraestructura.out.feign.adapter.UserApiAdapter;
import com.pragma.plazoleta.infraestructura.out.feign.adapter.MessageApiAdapter;
import com.pragma.plazoleta.infraestructura.out.feign.mapper.MessageRequestMapper;
import com.pragma.plazoleta.infraestructura.out.feign.mapper.UserDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IDishRepository dishRepository;
    private final ICategoryRepository categoryRepository;
    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final CategoryEntityMapper categoryEntityMapper;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final DishEntityMapper dishEntityMapper;
    private final OrderDishEntityMapper orderDishEntityMapper;
    private final UserDetailMapper userDetailMapper;
    private final MessageRequestMapper messageMapper;
    private final UserFeignClient userFeignClient;
    private final MessageFeignClient messageFeignClient;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort() {
        return new OrderDishJpaAdapter(orderDishRepository, orderDishEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(orderPersistencePort(), orderDishPersistencePort(),
                restaurantServicePort(), dishServicePort(), notificationServicePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }


    @Bean
    public IDishPersistencePort dishPersistencePort() {

        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort());
    }

    @Bean
    public IMessageServicePort messageServicePort(){
        return new MessageApiAdapter(messageFeignClient, messageMapper);
    }

    public IApiUserServicePort apiUserServicePort(){
        return new UserApiAdapter(userFeignClient, userDetailMapper);
    }

    @Bean
    public UserApiAdapter apiUserAdapter(UserFeignClient userApiClient) {
        return new UserApiAdapter(userFeignClient, userDetailMapper);
    }

    @Bean
    public ITraceabilityApiServicePort traceabilityApiServicePort(){
        return new TraceabilityApiAdapter();

    }

//    @Bean
//    public MessageUserAdapter messageUserAdapter(MessageFeignClient messageApiClient) {
//        return new MessageUserAdapter(messageFeignClient, messageMapper);
//    }

    @Bean ITraceabilityServicePort traceabilityServicePort(){
        return new TraceabilityUseCase(apiUserServicePort());
    }

    @Bean
    public INotificationServicePort notificationServicePort() {
        return new NotificationUseCase(messageServicePort(), apiUserServicePort());
    }
}
