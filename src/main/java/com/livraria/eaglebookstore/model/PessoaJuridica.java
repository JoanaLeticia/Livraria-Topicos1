package com.livraria.eaglebookstore.model;

import javax.persistence.Entity;

@Entity
public class PessoaJuridica extends Cliente {
    private String nome;

    private String cnpj;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
