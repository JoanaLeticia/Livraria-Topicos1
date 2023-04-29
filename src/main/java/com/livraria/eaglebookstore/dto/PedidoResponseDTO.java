package com.livraria.eaglebookstore.dto;

import java.util.Date;

import com.livraria.eaglebookstore.model.Pedido;
import com.livraria.eaglebookstore.model.StatusPedido;

public record PedidoResponseDTO(
    Long id,
    Integer quantidadeTotal,
    Double valorTotal,
    Date data,
    StatusPedido status
) {
    public PedidoResponseDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getQuantidadeTotal(), pedido.getValorTotal(), pedido.getData(), pedido.getStatus());
    }
}
