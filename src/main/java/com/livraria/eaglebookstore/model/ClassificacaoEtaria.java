package com.livraria.eaglebookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ClassificacaoEtaria {
    LIVRE(1, "Livre"),
    DEZ(2, "+10"),
    DOZE(3, "+12"),
    QUATORZE(4, "+14"),
    DEZESSEIS(5, "+16"),
    DEZOITO(6, "+18");

    private int id;

    private String label;

    ClassificacaoEtaria(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static ClassificacaoEtaria valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (ClassificacaoEtaria classificacaoEtaria : ClassificacaoEtaria.values()) {
            if (id.equals(classificacaoEtaria.getId()))
                return classificacaoEtaria;
        }
        throw new IllegalArgumentException("Esse id é inválido:" + id);
    }
}
