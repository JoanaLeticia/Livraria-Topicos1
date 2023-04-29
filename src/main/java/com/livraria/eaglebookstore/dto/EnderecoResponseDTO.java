package com.livraria.eaglebookstore.dto;

import com.livraria.eaglebookstore.model.Endereco;

public record EnderecoResponseDTO(
    Long id,
    boolean principal,
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String cep
) {

    public EnderecoResponseDTO(Endereco end) {
        this(end.getId(), end.isPrincipal(), end.getLogradouro(), end.getNumero(), end.getComplemento(), end.getBairro(), end.getCep());
    }

}
