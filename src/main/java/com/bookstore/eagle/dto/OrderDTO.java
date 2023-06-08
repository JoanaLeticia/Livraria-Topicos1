package com.bookstore.eagle.dto;

import java.time.LocalDate;
import java.util.List;

import io.smallrye.common.constraint.NotNull;

public record OrderDTO (
    Integer totalQuantity,

    @NotNull
    Double totalValue,

    LocalDate date,

    Integer status,

    Long clientId,

    List<RequestedItemDTO> RequestedItem,

    Long addressId
){
    
}
