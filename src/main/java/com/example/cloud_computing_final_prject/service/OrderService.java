package com.example.cloud_computing_final_prject.service;


import com.example.cloud_computing_final_prject.configuration.DataSourceRouting;
import com.example.cloud_computing_final_prject.data.OrderData;
import com.example.cloud_computing_final_prject.data.OrderEntity;
import com.example.cloud_computing_final_prject.data.OrderStatus;
import com.example.cloud_computing_final_prject.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderStatus checkOrderStatus(int orderId) {
        DataSourceRouting.setDataSource("SLAVE");
        OrderEntity entity = orderRepository.findById(orderId).get();
        DataSourceRouting.clear();
        return entity.getStatus();
    }

    @Transactional
    public String registerOrder(OrderData orderData){
        DataSourceRouting.setDataSource("MASTER");
        OrderEntity entity = new OrderEntity();
        entity.setUsername(orderData.getUsername());
        entity.setProduct(orderData.getProduct());
        entity.setQuantity(orderData.getQuantity());
        entity.setAddress(orderData.getAddress());
        entity.setStatus(OrderStatus.PENDING);
        OrderEntity saved = orderRepository.save(entity);
        DataSourceRouting.clear();
        return String.valueOf(saved.getOrderId());
    }

    @Transactional
    public void updateOrder(OrderStatus orderStatus, int orderId){
        DataSourceRouting.setDataSource("SLAVE");
        OrderEntity entity = orderRepository.findById(orderId).get();
        DataSourceRouting.clear();
        DataSourceRouting.setDataSource("MASTER");
        entity.setStatus(orderStatus);
        orderRepository.save(entity);
        DataSourceRouting.clear();
    }

}
