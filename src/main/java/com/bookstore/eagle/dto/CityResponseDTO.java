package com.bookstore.eagle.dto;

import com.bookstore.eagle.model.State;
import com.bookstore.eagle.model.City;

public record CityResponseDTO (
    Long id,

    String name,

    State state
) {

    public CityResponseDTO(City city) {
        this(city.getId(), city.getName(), city.getState());
    }

}
