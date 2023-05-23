package com.bookstore.eagle.dto;

import java.util.List;

import com.bookstore.eagle.model.Client;
import com.bookstore.eagle.model.Genre;
import com.bookstore.eagle.model.RequestedItem;
import com.bookstore.eagle.model.Product;
import com.bookstore.eagle.model.AgeRating;
import com.fasterxml.jackson.annotation.JsonInclude;

public record BookResponseDTO (
    Long id,
    String name,
    String description,
    String author,
    String publisher,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Genre genre,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    AgeRating rating,

    Double price,
    Integer stock,
    List<RequestedItem> requestedItems,
    List<Client> clients
) {
    public BookResponseDTO(Product product) {
        this(product.getId(),
        product.getName(),
        product.getDescription(),
        product.getAuthor(),
        product.getPublisher(),
        product.getGenre(),
        product.getAgeRating(),
        product.getPrice(),
        product.getStock(),
        product.getRequestedItems(),
        product.getClients());
    }
}
