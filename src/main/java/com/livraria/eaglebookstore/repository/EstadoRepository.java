package com.livraria.eaglebookstore.repository;

import com.livraria.eaglebookstore.model.Estado;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {

    public Estado findById(Long id) {
        return this.findById(id);
    }

    public void persist(Estado estado) {
        this.persist(estado);
    }

    public void delete(Long id) {
        this.delete("id", id);
    }

}
