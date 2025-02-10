package com.pragma.plazoleta.domain.utils;

public enum StateOrder {
        PENDING("PENDING"),
        PREPARATION("IN_PREPARATION"),
        READY("READY"),
        DELIVERED("DELIVERED"),
        CANCELED("CANCELED");

        private final String status;

        StateOrder(String status) {
                this.status = status;
        }

        public String getStatus() {
                return this.status;
        }
}
