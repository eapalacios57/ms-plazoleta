package com.pragma.plazoleta.domain.model;

import java.time.LocalDateTime;

public class Traceablility {

    private Long orderId;

    private UserDetail userDetailClient;

    private UserDetail userDetailEmployee;

    private LocalDateTime date;

    private String previousState;

    private String newSate;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UserDetail getUserDetailClient() {
        return userDetailClient;
    }

    public void setUserDetailClient(UserDetail userDetailClient) {
        this.userDetailClient = userDetailClient;
    }

    public UserDetail getUserDetailEmployee() {
        return userDetailEmployee;
    }

    public void setUserDetailEmployee(UserDetail userDetailEmployee) {
        this.userDetailEmployee = userDetailEmployee;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPreviousState() {
        return previousState;
    }

    public void setPreviousState(String previousState) {
        this.previousState = previousState;
    }

    public String getNewSate() {
        return newSate;
    }

    public void setNewSate(String newSate) {
        this.newSate = newSate;
    }
}
