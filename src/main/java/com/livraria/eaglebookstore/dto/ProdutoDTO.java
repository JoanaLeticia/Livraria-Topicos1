package com.livraria.eaglebookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProdutoDTO (

    @NotBlank(message = "Informe o nome.")
    @Size(max = 100)
    String nome,

    @NotBlank(message = "Informe a descrição.")
    @Size(max = 500)
    String descricao,

    Double preco,

    Integer estoque
){
    
}
