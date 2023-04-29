package com.livraria.eaglebookstore.repository;

import javax.enterprise.context.ApplicationScoped;

import com.livraria.eaglebookstore.model.Municipio;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepository<Municipio> {

    public void persist(Municipio municipio) {
        this.persist(municipio);
    }

    public Municipio findById(Long id) {
        return this.findById(id);
    }

    public void update(Municipio municipio) {
        this.update(municipio);
    }

    public void delete(Long id) {
        this.deleteById(id);
    }
}
