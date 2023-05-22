package com.livraria.eaglebookstore.dto;

import java.util.List;

import com.livraria.eaglebookstore.model.Pedido;
import com.livraria.eaglebookstore.model.PessoaJuridica;
import com.livraria.eaglebookstore.model.Produto;

public record PessoaJuridicaResponseDTO (
    Long id,
    String nome,
    String cnpj,
    String email,
    List<Pedido> pedidos,
    List<Produto> listaDesejos
){
    public PessoaJuridicaResponseDTO(PessoaJuridica pj) {
        this(pj.getId(),
        pj.getNome(),
        pj.getCnpj(),
        pj.getEmail(),
        pj.getPedidos(),
        pj.getListaDesejos());
    }
}
