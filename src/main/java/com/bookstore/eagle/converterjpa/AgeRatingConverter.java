package com.bookstore.eagle.converterjpa;

import com.bookstore.eagle.model.AgeRating;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AgeRatingConverter implements AttributeConverter<AgeRating, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(AgeRating ageRating) {
        return ageRating == null ? null : ageRating.getId();
    }

    @Override
    public AgeRating convertToEntityAttribute(Integer id) {
        return AgeRating.valueOf(id);
    }
}
