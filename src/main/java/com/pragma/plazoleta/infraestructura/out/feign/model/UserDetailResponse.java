package com.pragma.plazoleta.infraestructura.out.feign.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailResponse {

    private Long id;

    private String names;

    private String email;

    private Long restaurantId;

    private String phone;

    private String role;

}
