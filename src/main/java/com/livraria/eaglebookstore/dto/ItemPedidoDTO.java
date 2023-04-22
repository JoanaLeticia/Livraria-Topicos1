package com.livraria.eaglebookstore.dto;

import io.smallrye.common.constraint.NotNull;

public class ItemPedidoDTO {
    private Long id;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Double preco;

    @NotNull
    private Long produtoId;

    @NotNull
    private Long pedidoId;

    public ItemPedidoDTO(Long id, Integer quantidade, Double preco, Long produtoId, Long pedidoId) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produtoId = produtoId;
        this.pedidoId = pedidoId;
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

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

}
