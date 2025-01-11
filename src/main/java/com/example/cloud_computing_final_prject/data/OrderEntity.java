package com.example.cloud_computing_final_prject.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "username")
    private String username;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "address")
    private String address;
}
