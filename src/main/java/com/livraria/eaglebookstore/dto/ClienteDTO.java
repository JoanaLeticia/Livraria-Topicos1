package com.livraria.eaglebookstore.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

import com.livraria.eaglebookstore.model.Endereco;
import com.livraria.eaglebookstore.model.Pedido;
import com.livraria.eaglebookstore.model.Produto;
import com.livraria.eaglebookstore.model.Telefone;

public record ClienteDTO (

    @NotBlank(message = "Informe o email.")
    String email,

    List<Telefone> telefones,

    List<Endereco> enderecos,

    List<Pedido> pedidos,
    
    List<Produto> listaDesejos

) {

}
