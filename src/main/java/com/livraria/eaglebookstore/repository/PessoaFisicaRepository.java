package com.livraria.eaglebookstore.repository;

import com.livraria.eaglebookstore.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica> {

    public void create(PessoaFisica pessoaFisica) {
        persist(pessoaFisica);
    }

    public void update(PessoaFisica pessoaFisica) {
        update("nome = ?1, cpf = ?2, email = ?3, telefones = ?4, enderecos = ?5 where id = ?6",
        pessoaFisica.getNome(),
        pessoaFisica.getCpf(),
        pessoaFisica.getEmail(),
        pessoaFisica.getId());
    }

    public void delete(Long id) {
        deleteById(id);
    }

    public PessoaFisica searchById(Long id) {
        return findById(id);
    }

}