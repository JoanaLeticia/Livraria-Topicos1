package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.dto.ProdutoDTO;
import com.livraria.eaglebookstore.dto.ProdutoResponseDTO;
import java.util.List;

public interface ProdutoService {

    List<ProdutoResponseDTO> listarProdutos();

    ProdutoResponseDTO buscarProdutoPorId(Long id);

    ProdutoResponseDTO cadastrarProduto(ProdutoDTO dto);

    ProdutoResponseDTO atualizarProduto(Long id, ProdutoDTO dto);

    void excluirProduto(Long id);
}