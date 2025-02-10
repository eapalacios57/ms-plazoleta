package com.pragma.plazoleta.infraestructura.out.feign.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TraceabilityApiRequest {
    private Long orderId;

    private Long clientId;

    private Long employeeId;

    private String emailClient;

    private String emailEmployee;

    private LocalDateTime date;

    private String previousState;

    private String newSate;
}
