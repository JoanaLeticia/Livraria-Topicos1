package com.bookstore.eagle.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Genre {
    ROMANCE(1, "Romance"),
    COMEDY(2, "Comedy"),
    FANTASY(3, "Fantasy"),
    HORROR(4, "Horror"),
    CRIME(5, "Crime"),
    BIOGRAPHY(6, "Biography"),
    HISTORICAL(7, "Historical"),
    POETRY(8, "Poetry"),
    ADVENTURE(9, "Adventure"),
    DRAMA(10, "Drama");

    private int id;

    private String label;

    Genre(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Genre valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Genre genre : Genre.values()) {
            if (id.equals(genre.getId()))
                return genre;
        }
        throw new IllegalArgumentException("Invalid id:" + id);
    }

}
