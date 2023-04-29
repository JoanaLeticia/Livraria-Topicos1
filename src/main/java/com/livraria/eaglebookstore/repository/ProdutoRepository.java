package com.livraria.eaglebookstore.repository;

import com.livraria.eaglebookstore.model.Produto;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

}