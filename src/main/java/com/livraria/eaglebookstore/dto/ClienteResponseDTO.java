package com.livraria.eaglebookstore.dto;

import com.livraria.eaglebookstore.model.Cliente;

public record ClienteResponseDTO(
    Long id,
    String email
) {

    public ClienteResponseDTO(Cliente c) {
        this(c.getId(), c.getEmail());
    }

}
