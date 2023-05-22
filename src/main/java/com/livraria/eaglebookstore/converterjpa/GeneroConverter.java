package com.livraria.eaglebookstore.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.livraria.eaglebookstore.model.Genero;

@Converter(autoApply = true)
public class GeneroConverter implements AttributeConverter<Genero, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(Genero genero) {
        return genero == null ? null : genero.getId();
    }

    @Override
    public Genero convertToEntityAttribute(Integer id) {
        return Genero.valueOf(id);
    }
}
