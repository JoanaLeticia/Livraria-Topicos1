package com.livraria.eaglebookstore.dto;

import io.smallrye.common.constraint.NotNull;

public record ItemPedidoDTO(

    @NotNull
    Integer quantidade,

    @NotNull
    Double preco,

    @NotNull
    Long produtoId,

    @NotNull
    Long pedidoId
    
) {
    
}
