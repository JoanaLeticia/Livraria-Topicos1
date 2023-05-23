package com.bookstore.eagle.repository;

import com.bookstore.eagle.model.State;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StateRepository implements PanacheRepository<State> {

    public State findById(Long id) {
        return this.findById(id);
    }

    public void persist(State state) {
        this.persist(state);
    }

    public void delete(Long id) {
        this.delete("id", id);
    }

}
