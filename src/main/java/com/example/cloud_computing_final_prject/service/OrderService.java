package com.example.cloud_computing_final_prject.service;


import com.example.cloud_computing_final_prject.data.OrderData;
import com.example.cloud_computing_final_prject.data.OrderEntity;
import com.example.cloud_computing_final_prject.data.OrderStatus;
import com.example.cloud_computing_final_prject.repository.OrderRepository;
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

    public OrderStatus checkOrderStatus(int orderId) {
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
        entity.setStatus(OrderStatus.PENDING);
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
        entity.setStatus(orderStatus);
        orderRepository.merge(entity);
        log.info("Order with id {} updated", orderId);
    }

}
