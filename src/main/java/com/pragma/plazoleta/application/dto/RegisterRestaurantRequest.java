package com.pragma.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRestaurantRequest {

    private  String name;
    private String address;
    private Long ownerId;
    private String phone;
    private String nit;
    private String urlLogo;
}
