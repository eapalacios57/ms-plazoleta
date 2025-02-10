package com.pragma.plazoleta.infraestructura.out.feign.rest;

import com.pragma.plazoleta.infraestructura.configuration.FeignClientConfig;
import com.pragma.plazoleta.infraestructura.out.feign.model.UserDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-API", url = "${user.api.url}", configuration = FeignClientConfig.class)
public interface UserFeignClient {

    @GetMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDetailResponse getUserDetailById(@RequestParam Long userId);

 }
