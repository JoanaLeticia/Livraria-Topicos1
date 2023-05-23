package com.bookstore.eagle.repository;

import jakarta.enterprise.context.ApplicationScoped;

import com.bookstore.eagle.model.RequestedItem;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class RequestedItemRepository implements PanacheRepository<RequestedItem> {

    public RequestedItem save(RequestedItem requestedItem) {
        return null;
    }

}
