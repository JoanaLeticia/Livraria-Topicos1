package com.livraria.eaglebookstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
