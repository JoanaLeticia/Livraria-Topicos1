package com.bookstore.eagle.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

import com.bookstore.eagle.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    public List<Address> findByClientId(Long clientId) {
        return list("client.id", clientId);
    }

    public Address findByIdAndClientId(Long id, Long clientId) {
        return find("id = ?1 and client.id = ?2", id, clientId).firstResult();
    }
}
