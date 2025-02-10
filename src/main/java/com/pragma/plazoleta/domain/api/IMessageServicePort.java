package com.pragma.plazoleta.domain.api;


import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.UserDetail;

public interface IMessageServicePort {
    void messageSendCodeClient(Order order, UserDetail userDetail);
}
