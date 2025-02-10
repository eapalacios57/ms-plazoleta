package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IApiUserServicePort;
import com.pragma.plazoleta.domain.api.IMessageServicePort;
import com.pragma.plazoleta.domain.api.INotificationServicePort;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.UserDetail;


public class NotificationUseCase implements INotificationServicePort {

    private final IMessageServicePort messageServicePort;
    private final IApiUserServicePort apiUserServicePort;

    public NotificationUseCase(IMessageServicePort messageServicePort, IApiUserServicePort apiUserServicePort) {
        this.messageServicePort = messageServicePort;
        this.apiUserServicePort = apiUserServicePort;
    }

    @Override
    public void notifyClient(Order order) {
        UserDetail client = apiUserServicePort.getUserDetail(order.getClient().getId());
        messageServicePort.messageSendCodeClient(order, client);
    }
}
