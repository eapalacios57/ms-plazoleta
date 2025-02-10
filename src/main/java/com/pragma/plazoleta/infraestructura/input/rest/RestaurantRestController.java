package com.pragma.plazoleta.infraestructura.input.rest;

import com.pragma.plazoleta.application.dto.MessageResponse;
import com.pragma.plazoleta.application.dto.RegisterRestaurantRequest;
import com.pragma.plazoleta.application.dto.RestaurantsResponse;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageResponse> registerRestaurant(@Valid @RequestBody RegisterRestaurantRequest registerRestaurant){
        try{
            restaurantHandler.saveRestaurant(registerRestaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Restaurant created successfully."));

        }catch (IllegalAccessException exception){
            return new ResponseEntity<>(new MessageResponse(exception.getLocalizedMessage()),HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping
    public ResponseEntity<RestaurantsResponse> getAllRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        RestaurantsResponse restaurants = restaurantHandler.getAllRestaurants(page, size);
        return ResponseEntity.ok(restaurants);
    }
}
