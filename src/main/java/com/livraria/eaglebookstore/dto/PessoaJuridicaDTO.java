package com.livraria.eaglebookstore.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaJuridicaDTO (

    @NotBlank(message = "Por favor, informe o e-mail.")
    String email,

    @NotBlank(message = "Por favor, informe o CPF.")
    @Size(max = 14)
    String cnpj,

    @NotBlank(message = "Por favor, informe o nome.")
    String nome,

    List<TelefoneDTO> telefones,

    List<EnderecoDTO> enderecos,

    List<PedidoDTO> pedidos,

    List<LivroDTO> produtos
)
{
}
