package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Order;

public interface INotificationServicePort {

    public void notifyClient(Order order);
}
