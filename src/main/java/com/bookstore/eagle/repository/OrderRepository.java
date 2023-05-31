package com.bookstore.eagle.repository;

import jakarta.enterprise.context.ApplicationScoped;
import com.bookstore.eagle.model.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    @PersistenceContext
    EntityManager entityManager;

    public void update(Order order) {
        entityManager.persist(order);
    }

}
