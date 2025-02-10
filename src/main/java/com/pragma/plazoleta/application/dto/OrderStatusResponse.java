package com.pragma.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderStatusResponse {

    List<OrdersStatusDTO> ordersList;
    int size;
    int page;
    long totalElements;
    int totalPage;
}
