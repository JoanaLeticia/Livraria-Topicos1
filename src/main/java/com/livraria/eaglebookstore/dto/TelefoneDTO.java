package com.livraria.eaglebookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TelefoneDTO (
    Long id,

    @NotBlank(message = "Informe o DDD.")
    @Size(max = 2)
    Integer ddd,

    @NotBlank(message = "Informe o número.")
    @Size(max = 9)
    String numero

) {    
}
