package com.livraria.eaglebookstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
