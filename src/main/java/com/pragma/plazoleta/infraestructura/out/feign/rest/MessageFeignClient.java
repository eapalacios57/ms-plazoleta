package com.pragma.plazoleta.infraestructura.out.feign.rest;

import com.pragma.plazoleta.infraestructura.out.feign.model.MessageApiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MESSAGE-API", url = "${message.api.url}")
public interface MessageFeignClient {

    @PostMapping(value = "/sms/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendPinMessage(@RequestBody MessageApiRequest messageApiRequest);
}
