package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.Traceablility;

public interface ITraceabilityServicePort {

    Traceablility modelSendTraceabilityLogs(Order order, String statusOld);
}
