package com.pragma.plazoleta.infraestructura.input.rest;


import com.pragma.plazoleta.application.dto.MessageResponse;
import com.pragma.plazoleta.application.dto.OrderRequest;
import com.pragma.plazoleta.application.dto.OrderStatusResponse;
import com.pragma.plazoleta.application.handler.impl.OrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {
    private  final OrderHandler orderHandler;

    @PostMapping
    public ResponseEntity<MessageResponse> createOrder(@RequestBody OrderRequest orderRequest){
        orderHandler.createOrder(orderRequest);
        return new ResponseEntity<>(new MessageResponse("Order created success"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<OrderStatusResponse> getOrderStatus(@RequestParam String status,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size ){
        return ResponseEntity.ok(orderHandler.getOrderStatus(status, page, size));
    }


    @PutMapping("/{orderId}/status")
    public ResponseEntity<MessageResponse> updateStatus(@PathVariable Long orderId, @RequestParam String status, @RequestParam(required = false) String pin){
        orderHandler.updateOrderStatus(orderId, status, pin);
        return new ResponseEntity<>(new MessageResponse("Order change the status to ready and notified to client"), HttpStatus.OK);
    }

}
