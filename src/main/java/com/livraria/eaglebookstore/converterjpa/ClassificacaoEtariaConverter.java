package com.livraria.eaglebookstore.converterjpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.livraria.eaglebookstore.model.ClassificacaoEtaria;

@Converter(autoApply = true)
public class ClassificacaoEtariaConverter implements AttributeConverter<ClassificacaoEtaria, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(ClassificacaoEtaria classificacaoEtaria) {
        return classificacaoEtaria == null ? null : classificacaoEtaria.getId();
    }

    @Override
    public ClassificacaoEtaria convertToEntityAttribute(Integer id) {
        return ClassificacaoEtaria.valueOf(id);
    }
}
