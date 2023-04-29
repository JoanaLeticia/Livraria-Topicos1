package com.livraria.eaglebookstore.dto;

import java.util.List;

import com.livraria.eaglebookstore.model.ItemPedido;
import com.livraria.eaglebookstore.model.Produto;
import com.livraria.eaglebookstore.model.Cliente;

public record ProdutoResponseDTO (
    Long id,

    String nome,

    String descricao,

    Double preco,

    Integer estoque,

    List<ItemPedido> itensPedido,

    List<Cliente> clientes

) {

    public ProdutoResponseDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getEstoque(), produto.getItensPedido(), produto.getClientes());;
    }

}
