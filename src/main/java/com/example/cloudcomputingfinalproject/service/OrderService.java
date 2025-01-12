package com.example.cloudcomputingfinalproject.service;


import com.example.cloudcomputingfinalproject.data.OrderData;
import com.example.cloudcomputingfinalproject.entity.OrderEntity;
import com.example.cloudcomputingfinalproject.data.OrderStatus;
import com.example.cloudcomputingfinalproject.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String checkOrderStatus(int orderId) {
        log.info("hello mahmood");
        OrderEntity entity = orderRepository.findById(orderId);
        if (entity == null) {
            log.error("Order with id {} not found", orderId);
        }
        return entity.getStatus();
    }

    @Transactional
    public String registerOrder(OrderData orderData){
        OrderEntity entity = new OrderEntity();
        entity.setUsername(orderData.getUsername());
        entity.setProduct(orderData.getProduct());
        entity.setQuantity(orderData.getQuantity());
        entity.setAddress(orderData.getAddress());
        entity.setStatus(OrderStatus.PENDING.getValue());
        OrderEntity saved = orderRepository.save(entity);
        if (saved == null)
            log.error("error in save");
        return String.valueOf(saved.getOrderId());
    }

    @Transactional
    public void updateOrder(OrderStatus orderStatus, int orderId){
        OrderEntity entity = orderRepository.findById(orderId);
        if (entity == null) {
            log.error("Order with id {} not found", orderId);
        }
        entity.setStatus(orderStatus.getValue());
        orderRepository.merge(entity);
        log.info("Order with id {} updated", orderId);
    }

}
