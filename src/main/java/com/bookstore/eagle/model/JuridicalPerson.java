package com.bookstore.eagle.model;

import jakarta.persistence.Entity;

@Entity
public class JuridicalPerson extends Client {
    private String name;

    private String cnpj;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
