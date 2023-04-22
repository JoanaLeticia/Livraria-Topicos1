package com.livraria.eaglebookstore.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.livraria.eaglebookstore.model.ClassificacaoEtaria;
import com.livraria.eaglebookstore.model.Cliente;
import com.livraria.eaglebookstore.model.Genero;
import com.livraria.eaglebookstore.model.ItemPedido;
import com.livraria.eaglebookstore.model.Produto;

public record LivroResponseDTO (
    Long id,
    String nome,
    String descricao,
    String autor,
    String editora,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Genero genero,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    ClassificacaoEtaria classificacaoEtaria,

    Double preco,
    Integer estoque,
    List<ItemPedido> itensPedido,
    List<Cliente> clientes
) {
    public LivroResponseDTO(Produto produto) {
        this(produto.getId(),
        produto.getNome(),
        produto.getDescricao(),
        produto.getAutor(),
        produto.getEditora(),
        produto.getGenero(),
        produto.getClassificacaoEtaria(),
        produto.getPreco(),
        produto.getEstoque(),
        produto.getItensPedido(),
        produto.getClientes());
    }
}
