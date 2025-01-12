package com.example.cloudcomputingfinalproject.data;

import lombok.Data;

@Data
public class OrderData {
    private String username;

    private String product;

    private int quantity;

    private String address;
}
