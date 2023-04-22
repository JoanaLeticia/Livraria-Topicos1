package com.livraria.eaglebookstore.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record PessoaFisicaDTO (

    @NotBlank(message = "Por favor, informe o e-mail.")
    String email,

    @NotBlank(message = "Por favor, informe o CPF.")
    @Size(max = 14)
    String cpf,

    @NotBlank(message = "Por favor, informe o nome.")
    String nome,

    List<TelefoneDTO> telefones,

    List<EnderecoDTO> enderecos,

    List<PedidoDTO> pedidos,

    List<LivroDTO> produtos

) {

}
