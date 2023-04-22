package com.livraria.eaglebookstore.dto;

import javax.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;

public class MunicipioDTO {
    
    @NotBlank
    private String nome;

    @NotNull
    private Long estadoId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

}
