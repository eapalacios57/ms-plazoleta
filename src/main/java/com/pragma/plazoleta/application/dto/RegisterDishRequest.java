package com.pragma.plazoleta.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDishRequest {

    @NotBlank(message = "The name must not be empty.")
    private String name;

    @NotBlank(message = "The description must not be empty.")
    private String description;

    @NotNull(message = "The price must not be empty.")
    @Min(value = 1, message = "The price must be greater than 0.")
    private Long price;

    @NotNull(message = "The categoryId must not be empty.")
    private Long categoryId;

    @NotBlank(message = "The restaurantId must not be empty.")
    private String urlImage;

    @NotBlank(message = "The restaurantId must not be empty.")
    private String restaurantId;

}
