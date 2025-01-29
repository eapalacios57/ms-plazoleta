package com.pragma.plazoleta.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private  String phone;
    private Long ownerId;
    private String urlLogo;
    private String nit;
}
