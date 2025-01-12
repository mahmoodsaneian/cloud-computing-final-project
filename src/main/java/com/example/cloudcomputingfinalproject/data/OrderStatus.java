package com.example.cloudcomputingfinalproject.data;

public enum OrderStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected"),;

    OrderStatus(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
