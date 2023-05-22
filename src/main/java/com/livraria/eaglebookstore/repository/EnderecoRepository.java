package com.livraria.eaglebookstore.repository;

import com.livraria.eaglebookstore.model.Endereco;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByClienteId(Long clienteId) {
        return list("cliente.id", clienteId);
    }

    public Endereco findByIdAndClienteId(Long id, Long clienteId) {
        return find("id = ?1 and cliente.id = ?2", id, clienteId).firstResult();
    }
}
