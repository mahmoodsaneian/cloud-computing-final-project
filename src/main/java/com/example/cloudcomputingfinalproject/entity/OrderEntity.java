package com.example.cloudcomputingfinalproject.entity;

import com.example.cloudcomputingfinalproject.data.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
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
    private String status;

    @Column(name = "address")
    private String address;
}
