package com.livraria.eaglebookstore.dto;

import java.util.List;

import com.livraria.eaglebookstore.model.Pedido;
import com.livraria.eaglebookstore.model.PessoaFisica;
import com.livraria.eaglebookstore.model.Produto;

public record PessoaFisicaResponseDTO (
    Long id,
    String nome,
    String cpf,
    String email,
    List<Pedido> pedidos,
    List<Produto> listaDesejos
){
    public PessoaFisicaResponseDTO(PessoaFisica pf) {
        this(pf.getId(),
        pf.getNome(),
        pf.getCpf(),
        pf.getEmail(),
        pf.getPedidos(),
        pf.getListaDesejos());
    }
}
