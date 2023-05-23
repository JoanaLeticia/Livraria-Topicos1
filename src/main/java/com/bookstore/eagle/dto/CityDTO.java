package com.bookstore.eagle.dto;

import jakarta.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;

public record CityDTO(
    @NotBlank
    String name,

    @NotNull
    Long stateId
) {

}
