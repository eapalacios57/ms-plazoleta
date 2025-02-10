package com.pragma.plazoleta.infraestructura.exception;

public class OrderInProcessException extends RuntimeException {
    public OrderInProcessException(String message) {
        super(message);
    }
}
