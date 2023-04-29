package com.livraria.eaglebookstore.dto;

import javax.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;

public record MunicipioDTO(
    @NotBlank
    String nome,

    @NotNull
    Long estadoId
) {

}
