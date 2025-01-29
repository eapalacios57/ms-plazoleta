package com.pragma.plazoleta.infraestructura.input.rest;

import com.pragma.plazoleta.application.dto.MessageResponse;
import com.pragma.plazoleta.application.dto.RegisterDishRequest;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.domain.model.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {
    private final IDishHandler dishHandler;

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping
    public ResponseEntity<MessageResponse> registerDish(@RequestBody RegisterDishRequest request) {
        try{
            dishHandler.saveDish(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Dish created successfully."));
        }catch (IllegalAccessException exception){
            return new ResponseEntity<>(new MessageResponse(exception.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/{dishId}")
    public ResponseEntity<MessageResponse> updateDish(@PathVariable Long dishId, @RequestBody RegisterDishRequest request) {
        try{
            dishHandler.updateDish(request, dishId);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Dish update successfully."));
        }catch (IllegalAccessException exception){
            return new ResponseEntity<>(new MessageResponse(exception.getLocalizedMessage()),HttpStatus.BAD_REQUEST);

        }
    }

    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/{dishId}/disabled")
    public ResponseEntity<MessageResponse> disabledDish(@PathVariable Long dishId) {
        try{
            dishHandler.disabledDish(dishId);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Dish disabled successfully."));
        }catch (IllegalAccessException exception){
            return new ResponseEntity<>(new MessageResponse(exception.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/{dishId}/enabled")
    public ResponseEntity<MessageResponse> enabledDish(@PathVariable Long dishId) {
        try{
            dishHandler.enabledDish(dishId);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Restaurant enabled successfully."));
        }catch (IllegalAccessException exception) {
            return new ResponseEntity<>(new MessageResponse(exception.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Dish>> getDishesByCategory(@RequestParam Long categoryId,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size)  {

            Page<Dish> dishes = dishHandler.getAllDishesByCategory(categoryId, page, size);
            return ResponseEntity.status(HttpStatus.OK).body(dishes);
    }

}
