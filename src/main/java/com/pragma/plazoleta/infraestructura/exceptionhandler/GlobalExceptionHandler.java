package com.pragma.plazoleta.infraestructura.exceptionhandler;

import com.pragma.plazoleta.application.dto.ApiError;
import com.pragma.plazoleta.infraestructura.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.core.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError<String>> handlerAccessDeniedException(HttpServletRequest request,
                                                          AccessDeniedException exception){

        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage("Access denied. You do not have the necessary permissions to access this function");
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError<String>> handleAuthenticationException(AuthenticationException ex) {

        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl("");
        apiError.setMessage("Authentication failed. Please verify your credentials.");
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl("");
        apiError.setMessage("Invalid argument. Please verify the data you are sending.");
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ApiError<Map<String, String>> apiError = new ApiError<>();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        apiError.setBackendMessage(errors);
        apiError.setUrl("");
        apiError.setMessage("Invalid argument. Please verify the data you are sending.");
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError<String>> handleJwtException(JwtException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl("");
        apiError.setMessage("Error con el JWT");
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiError<String>> handleJwtException(ExpiredJwtException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl("");
        apiError.setMessage("Error con el JWT");
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomConnectionApiException.class)
    public ResponseEntity<ApiError<String>> customConnectionApiException(CustomConnectionApiException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage("Error connecting to user service.");
        apiError.setUrl("");
        apiError.setMessage("Error connecting to user service.");
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ModifyDishException.class)
    public ResponseEntity<ApiError<String>> modifyDishException(ModifyDishException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage("You cannot modify the dish.");
        apiError.setUrl("");
        apiError.setMessage("You cannot modify the dish.");
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError<String>> notFoundException(NotFoundException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getMessage());
        apiError.setUrl("");
        apiError.setMessage(ex.getMessage());
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderInProcessException.class)
    public ResponseEntity<ApiError<String>> orderInProcessException(OrderInProcessException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getMessage());
        apiError.setUrl("");
        apiError.setMessage(ex.getMessage());
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
