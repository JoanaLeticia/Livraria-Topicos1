package com.livraria.eaglebookstore.repository;

import com.livraria.eaglebookstore.model.Livro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LivroRepository implements PanacheRepository<Livro> {

    public void create(Livro livro) {
        persist(livro);
    }

    public void update(Livro livro) {
        update("nome = ?1, descricao = ?2, preco = ?3, estoque = ?4, autor = ?5, editora = ?6, genero = ?7, classificacaoEtaria = ?8 where id = ?9",
        livro.getNome(),
        livro.getDescricao(),
        livro.getPreco(),
        livro.getEstoque(),
        livro.getAutor(),
        livro.getEditora(),
        livro.getGenero(),
        livro.getClassificacaoEtaria(),
        livro.getId());
    }

    public void delete(Long id) {
        deleteById(id);
    }

    public Livro searchById(Long id) {
        return findById(id);
    }

}
