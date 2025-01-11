package com.example.cloud_computing_final_prject.data;

import lombok.Data;

@Data
public class OrderData {
    private String username;

    private String product;

    private int quantity;

    private String address;
}
