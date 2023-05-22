package com.livraria.eaglebookstore.dto;

import jakarta.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;

public record LivroDTO (

    @NotBlank(message = "Informe o nome.")
    String nome,

    @NotBlank(message = "Informe a descrição.")
    String descricao,

    @NotBlank
    String autor,

    @NotBlank
    String editora,

    Integer genero,

    Integer classificacaoEtaria,

    @NotNull
    Double preco,

    @NotNull
    Integer estoque

) {

}
