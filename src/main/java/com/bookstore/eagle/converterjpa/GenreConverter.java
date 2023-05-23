package com.bookstore.eagle.converterjpa;

import com.bookstore.eagle.model.Genre;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(Genre genre) {
        return genre == null ? null : genre.getId();
    }

    @Override
    public Genre convertToEntityAttribute(Integer id) {
        return Genre.valueOf(id);
    }
}
