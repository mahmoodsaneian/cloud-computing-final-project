package com.example.cloudcomputingfinalproject.controller;

import com.example.cloudcomputingfinalproject.data.OrderData;
import com.example.cloudcomputingfinalproject.data.OrderStatus;
import com.example.cloudcomputingfinalproject.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/check-order-status/{orderId}")
    public String checkOrderStatus(@PathVariable int orderId) {
        return orderService.checkOrderStatus(orderId);
    }

    @PostMapping("/register-order")
    public String registerOrder(@RequestBody OrderData orderData) {
        return orderService.registerOrder(orderData);
    }

    @PutMapping("/update-order/{orderId}")
    public void updateOrder(@RequestBody OrderStatus orderStatus, @PathVariable int orderId) {
        orderService.updateOrder(orderStatus, orderId);
    }
}
