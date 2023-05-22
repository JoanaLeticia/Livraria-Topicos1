package com.livraria.eaglebookstore.repository;

import com.livraria.eaglebookstore.model.PessoaJuridica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaJuridicaRepository implements PanacheRepository<PessoaJuridica> {

    public void create(PessoaJuridica pessoaJuridica) {
        persist(pessoaJuridica);
    }

    public void update(PessoaJuridica pessoaJuridica) {
        update("nome = ?1, cnpj = ?2, email = ?3, telefones = ?4, enderecos = ?5 where id = ?6",
        pessoaJuridica.getNome(),
        pessoaJuridica.getCnpj(),
        pessoaJuridica.getEmail(),
        pessoaJuridica.getId());
    }

    public void delete(Long id) {
        deleteById(id);
    }

    public PessoaJuridica searchById(Long id) {
        return findById(id);
    }

}
