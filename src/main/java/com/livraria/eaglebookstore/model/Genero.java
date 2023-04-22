package com.livraria.eaglebookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Genero {
    ROMANCE(1, "Romance"),
    COMEDIA(2, "Comédia"),
    FANTASIA(3, "Fantasia"),
    TERROR(4, "Terror"),
    POLICIAL(5, "Policial"),
    BIOGRAFIA(6, "Biografia"),
    HISTORICO(7, "Histórico"),
    POESIA(8, "Poesia"),
    AVENTURA(9, "Aventura"),
    DRAMA(10, "Drama");

    private int id;

    private String label;

    Genero(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Genero valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Genero genero : Genero.values()) {
            if (id.equals(genero.getId()))
                return genero;
        }
        throw new IllegalArgumentException("Esse id é inválido:" + id);
    }

}
