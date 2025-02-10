package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IApiUserServicePort;
import com.pragma.plazoleta.domain.api.ITraceabilityServicePort;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.Traceablility;
import com.pragma.plazoleta.domain.model.UserDetail;

import java.time.LocalDateTime;

public class TraceabilityUseCase implements ITraceabilityServicePort {

    private final IApiUserServicePort apiUserServicePort;

    public TraceabilityUseCase(IApiUserServicePort apiUserServicePort) {
        this.apiUserServicePort = apiUserServicePort;
    }


    @Override
    public Traceablility modelSendTraceabilityLogs(Order order, String statusOld) {
        Traceablility traceablility = new Traceablility();
        UserDetail userClient = apiUserServicePort.getUserDetail(order.getClient().getId());
        UserDetail userEmployee = apiUserServicePort.getUserDetail(order.getClient().getId());
        traceablility.setDate(LocalDateTime.now());
        traceablility.setUserDetailClient(userClient);
        traceablility.setUserDetailEmployee(userEmployee);
        traceablility.setPreviousState(statusOld);
        traceablility.setNewSate(order.getStatus());
        return traceablility;
    }
}
