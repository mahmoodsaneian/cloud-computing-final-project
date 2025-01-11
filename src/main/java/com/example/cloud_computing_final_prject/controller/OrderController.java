package com.example.cloud_computing_final_prject.controller;

import com.example.cloud_computing_final_prject.data.OrderData;
import com.example.cloud_computing_final_prject.data.OrderStatus;
import com.example.cloud_computing_final_prject.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/check-order-status/{orderId}")
    public OrderStatus checkOrderStatus(@PathVariable int orderId) {
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
