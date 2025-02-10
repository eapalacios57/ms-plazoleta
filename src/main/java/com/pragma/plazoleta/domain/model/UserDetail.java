package com.pragma.plazoleta.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetail {

    private Long id;

    private String names;

    private String email;

    private Long restaurantId;

    private String role;

    private String token;

    private String phone;
}
