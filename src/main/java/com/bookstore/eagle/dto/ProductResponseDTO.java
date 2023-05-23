package com.bookstore.eagle.dto;

import java.util.List;

import com.bookstore.eagle.model.Client;
import com.bookstore.eagle.model.RequestedItem;
import com.bookstore.eagle.model.Product;

public record ProductResponseDTO (
    Long id,

    String name,

    String description,

    Double price,

    Integer stock,

    List<RequestedItem> requestedItems,

    List<Client> clients

) {

    public ProductResponseDTO(Product product) {
        this(product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getStock(), 
        product.getRequestedItems(),
        product.getClients());;
    }

}
