package com.bookstore.eagle.repository;

import jakarta.enterprise.context.ApplicationScoped;

import com.bookstore.eagle.model.City;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CityRepository implements PanacheRepository<City> {

    public void persist(City city) {
        this.persist(city);
    }

    public City findById(Long id) {
        return this.findById(id);
    }

    public void update(City city) {
        this.update(city);
    }

    public void delete(Long id) {
        this.deleteById(id);
    }
}
