package com.bookstore.eagle.repository;

import com.bookstore.eagle.model.JuridicalPerson;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JuridicalPersonRepository implements PanacheRepository<JuridicalPerson> {

    public void create(JuridicalPerson juridicalPerson) {
        persist(juridicalPerson);
    }

    public void update(JuridicalPerson juridicalPerson) {
        update("name = ?1, cnpj = ?2, email = ?3, where id = ?4",
        juridicalPerson.getName(),
        juridicalPerson.getCnpj(),
        juridicalPerson.getEmail(),
        juridicalPerson.getId());
    }

    public void delete(Long id) {
        deleteById(id);
    }

    public JuridicalPerson searchById(Long id) {
        return findById(id);
    }

}
