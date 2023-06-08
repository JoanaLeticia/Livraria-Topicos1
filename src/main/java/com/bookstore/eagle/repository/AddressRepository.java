package com.bookstore.eagle.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

import com.bookstore.eagle.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    public List<Address> findByUserId(Long userId) {
        return list("user.id", userId);
    }

    public Address findByIdAndUserId(Long id, Long userId) {
        return find("id = ?1 and user.id = ?2", id, userId).firstResult();
    }
}
