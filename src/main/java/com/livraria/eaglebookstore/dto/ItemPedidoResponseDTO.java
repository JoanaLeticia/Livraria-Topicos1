package com.livraria.eaglebookstore.dto;

import com.livraria.eaglebookstore.model.ItemPedido;
import com.livraria.eaglebookstore.model.Produto;

public record ItemPedidoResponseDTO(
        Long id,
        Integer quantidade,
        Double preco,
        Produto produto) {

    public ItemPedidoResponseDTO(ItemPedido ip) {
        this(ip.getId(), ip.getQuantidade(), ip.getPreco(), ip.getProduto());
    }

}
