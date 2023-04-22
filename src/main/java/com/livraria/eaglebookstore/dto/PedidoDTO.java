package com.livraria.eaglebookstore.dto;

import java.time.LocalDate;
import java.util.List;

import io.smallrye.common.constraint.NotNull;

public record PedidoDTO (
    Integer quantidadeTotal,

    @NotNull
    Double valorTotal,

    LocalDate data,

    Integer status,

    @NotNull
    Long clienteId,

    List<ItemPedidoDTO> itensPedido,

    @NotNull
    Long enderecoId
){
    
}
