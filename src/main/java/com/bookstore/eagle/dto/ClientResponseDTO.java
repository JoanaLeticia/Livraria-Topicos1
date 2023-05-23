package com.bookstore.eagle.dto;

import com.bookstore.eagle.model.Client;

public record ClientResponseDTO(
    Long id,
    String email
) {

    public ClientResponseDTO(Client client) {
        this(client.getId(), client.getEmail());
    }

}
