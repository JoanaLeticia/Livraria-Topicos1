package com.bookstore.eagle.repository;

import jakarta.enterprise.context.ApplicationScoped;

import com.bookstore.eagle.model.Order;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

}
