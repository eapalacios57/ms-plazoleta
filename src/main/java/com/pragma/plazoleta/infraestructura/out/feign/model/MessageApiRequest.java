package com.pragma.plazoleta.infraestructura.out.feign.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageApiRequest {
    private  String names;

    private String phone;

    private String pin;
}
