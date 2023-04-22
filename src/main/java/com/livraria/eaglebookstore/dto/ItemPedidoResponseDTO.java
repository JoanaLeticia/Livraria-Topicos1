package com.livraria.eaglebookstore.dto;

import com.livraria.eaglebookstore.model.Produto;

public class ItemPedidoResponseDTO {

    private Long id;
    private Integer quantidade;
    private Double preco;
    private Produto produto;

    public ItemPedidoResponseDTO(Long id, Integer quantidade, Double preco, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
