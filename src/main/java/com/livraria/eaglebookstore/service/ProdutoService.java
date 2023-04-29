package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.dto.ProdutoDTO;
import com.livraria.eaglebookstore.dto.ProdutoResponseDTO;
import com.livraria.eaglebookstore.model.Produto;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface ProdutoService {

    List<Produto> listarProdutos();

    ProdutoResponseDTO buscarProdutoPorId(Long id);

    ProdutoResponseDTO cadastrarProduto(ProdutoDTO dto);

    ProdutoResponseDTO atualizarProduto(Long id, ProdutoDTO dto);

    boolean excluirProduto(Long id);
}