package com.livraria.eaglebookstore.converterjpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
