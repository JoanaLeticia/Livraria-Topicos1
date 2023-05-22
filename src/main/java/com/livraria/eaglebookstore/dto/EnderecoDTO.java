package com.livraria.eaglebookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
    boolean principal,

    @NotBlank(message = "Informe o logradouro.")
    String logradouro,

    @NotBlank(message = "Informe o número.")
    String numero,
    
    String complemento,

    @NotBlank(message = "Informe o bairro.")
    String bairro,

    @NotBlank(message = "Informe o CEP.")
    @Size(max = 8)
    String cep,

    Long municipioId,

    Long clienteId
) {
    
}
