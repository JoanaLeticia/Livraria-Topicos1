package com.livraria.eaglebookstore.dto;

import com.livraria.eaglebookstore.model.Estado;
import com.livraria.eaglebookstore.model.Municipio;

public record MunicipioResponseDTO (
    Long id,

    String nome,

    Estado estado
) {

    public MunicipioResponseDTO(Municipio municipio) {
        this(municipio.getId(), municipio.getNome(), municipio.getEstado());
    }

}
