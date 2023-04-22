package com.livraria.eaglebookstore.model;

import javax.persistence.Entity;

@Entity
public class PessoaJuridica extends Cliente {
    private String nome;

    private String cpnj;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpnj() {
        return cpnj;
    }

    public void setCpnj(String cpnj) {
        this.cpnj = cpnj;
    }

}
