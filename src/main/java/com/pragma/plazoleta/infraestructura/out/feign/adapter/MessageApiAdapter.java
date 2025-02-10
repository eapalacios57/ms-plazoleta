package com.pragma.plazoleta.infraestructura.out.feign.adapter;

import com.pragma.plazoleta.domain.api.IMessageServicePort;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.UserDetail;
import com.pragma.plazoleta.infraestructura.out.feign.rest.MessageFeignClient;
import com.pragma.plazoleta.infraestructura.out.feign.mapper.MessageRequestMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MessageApiAdapter implements IMessageServicePort {
    private final MessageFeignClient messageFeignClient;
    private final MessageRequestMapper messageMapper;


    @Override
    public void messageSendCodeClient(Order order, UserDetail userDetail) {
        messageFeignClient.sendPinMessage(messageMapper.toMessageRequest(order, userDetail));
    }
}
