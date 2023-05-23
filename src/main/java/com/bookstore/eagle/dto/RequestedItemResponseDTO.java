package com.bookstore.eagle.dto;

import com.bookstore.eagle.model.RequestedItem;
import com.bookstore.eagle.model.Product;

public record RequestedItemResponseDTO(
        Long id,
        Integer quantity,
        Double price,
        Product product) {

    public RequestedItemResponseDTO(RequestedItem ip) {
        this(ip.getId(), ip.getQuantity(), ip.getPrice(), ip.getProduct());
    }

}
