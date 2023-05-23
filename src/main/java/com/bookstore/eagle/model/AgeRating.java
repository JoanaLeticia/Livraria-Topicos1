package com.bookstore.eagle.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AgeRating {
    ALL_AGES(1, "All Ages"),
    TEN(2, "10 and Older"),
    TWELVE(3, "12 and Older"),
    FOURTEEN(4, "14 and Older"),
    SIXTEEN(5, "16 and Older"),
    MATURE(6, "Mature");

    private int id;

    private String label;

    AgeRating(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static AgeRating valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (AgeRating rating : AgeRating.values()) {
            if (id.equals(rating.getId()))
                return rating;
        }
        throw new IllegalArgumentException("Invalid id:" + id);
    }
}
