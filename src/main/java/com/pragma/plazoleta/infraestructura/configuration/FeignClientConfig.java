package com.pragma.plazoleta.infraestructura.configuration;

import com.pragma.plazoleta.domain.model.UserDetail;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfig {

    @Bean
    Logger.Level feignLoggerLever(){ return Logger.Level.FULL; }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = getToken();
            requestTemplate.header("Authorization", token);
        };
    }

    private String getToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        return "Bearer " + userDetail.getToken();
    }
}
