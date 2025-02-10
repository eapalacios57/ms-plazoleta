package com.pragma.plazoleta.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDishRequest {

    @NotNull(message = "The price must not be empty.")
    @Min(value = 1, message = "The price must be greater than 0.")
    private Long price;

    @NotBlank(message = "The description must not be empty.")
    private String description;
}
