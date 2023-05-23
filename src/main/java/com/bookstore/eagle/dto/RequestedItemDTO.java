package com.bookstore.eagle.dto;

import io.smallrye.common.constraint.NotNull;

public record RequestedItemDTO(

    @NotNull
    Integer quantity,

    @NotNull
    Double price,

    @NotNull
    Long productId,

    @NotNull
    Long orderId
    
) {
    
}
