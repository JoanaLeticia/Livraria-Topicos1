package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.model.Produto;
import com.livraria.eaglebookstore.repository.ProdutoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    EntityManager entityManager;

    public List<Produto> listarProdutos() {
        return produtoRepository.listAll();
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
        public Produto cadastrarProduto(Produto produto) {
        produtoRepository.persistAndFlush(produto);
        return produto;
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setDescricao(produtoAtualizado.getDescricao());
            // Atualizar outras propriedades conforme necessário
            return entityManager.merge(produto);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean excluirProduto(Long id) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            produtoRepository.delete(produto);
            return true;
        } else {
            return false;
        }
    }
}