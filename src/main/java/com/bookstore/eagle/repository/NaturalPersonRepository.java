package com.bookstore.eagle.repository;

import com.bookstore.eagle.model.NaturalPerson;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NaturalPersonRepository implements PanacheRepository<NaturalPerson> {

    public void create(NaturalPerson naturalPerson) {
        persist(naturalPerson);
    }

    public void update(NaturalPerson naturalPerson) {
        update("name = ?1, cpf = ?2, email = ?3, where id = ?4",
        naturalPerson.getName(),
        naturalPerson.getCpf(),
        naturalPerson.getEmail(),
        naturalPerson.getId());
    }

    public void delete(Long id) {
        deleteById(id);
    }

    public NaturalPerson searchById(Long id) {
        return findById(id);
    }

}