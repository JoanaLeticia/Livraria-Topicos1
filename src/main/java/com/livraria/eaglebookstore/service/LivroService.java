package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.model.Livro;
import com.livraria.eaglebookstore.repository.LivroRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

@ApplicationScoped
public class LivroService {

    @Inject
    LivroRepository livroRepository;

    @Transactional
    public void cadastrarLivro(Livro livro) {
        livroRepository.persist(livro);
    }

    public Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id);
    }

    @Transactional
    public Livro atualizarLivro(Long id, Livro livro) {
        Livro livroExistente = buscarLivroPorId(id);
        livroExistente.setNome(livro.getNome());
        livroExistente.setDescricao(livro.getDescricao());
        livroExistente.setPreco(livro.getPreco());
        livroExistente.setEstoque(livro.getEstoque());
        livroExistente.setAutor(livro.getAutor());
        livroExistente.setEditora(livro.getEditora());
        livroExistente.setGenero(livro.getGenero());
        livroExistente.setClassificacaoEtaria(livro.getClassificacaoEtaria());
        return livroRepository.save(livroExistente);
    }

    @Transactional
    public void excluirLivro(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro save(@Valid Livro livro) {
        return null;
    }

    public List<Livro> listarLivros() {
        return livroRepository.listAll();
    }

}
