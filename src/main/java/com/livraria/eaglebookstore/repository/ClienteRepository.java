package com.livraria.eaglebookstore.repository;

import com.livraria.eaglebookstore.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

}