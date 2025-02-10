package com.pragma.plazoleta.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRestaurantRequest {

    @NotBlank(message = "The name must not be empty.")
    private  String name;

    @NotBlank(message = "The address must not be empty.")
    private String address;

    @NotNull(message = "The owner must not be empty.")
    private Long ownerId;

    @Pattern(regexp = "^\\+?\\d{1,13}", message = "The phone number can contain a maximum of 13 characters ")
    @NotBlank(message = "The phone must not be empty..")
    private String phone;

    @Pattern(regexp = "\\d+", message = "NIT must be numeric.")
    private String nit;

    @NotBlank(message = "The urlLogo must not be empty.")
    private String urlLogo;
}
