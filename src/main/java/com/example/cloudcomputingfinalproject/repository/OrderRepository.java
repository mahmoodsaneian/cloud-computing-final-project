package com.example.cloudcomputingfinalproject.repository;

import com.example.cloudcomputingfinalproject.entity.OrderEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderRepository {

    @PersistenceContext(unitName = "master")
    private EntityManager masterEntityManager;

    @PersistenceContext(unitName = "slave")
    private EntityManager slaveEntityManager;

    @Transactional(transactionManager = "transactionManager")
    public OrderEntity save(OrderEntity entity) {
        masterEntityManager.persist(entity);
        return entity;
    }

    @Transactional(transactionManager = "spareTransactionManager")
    public void merge(OrderEntity order) {
        masterEntityManager.merge(order);
    }

    @Transactional(readOnly = true, transactionManager = "spareTransactionManager")
    public OrderEntity findById(int id) {
        return slaveEntityManager.find(OrderEntity.class, id);
    }
}
