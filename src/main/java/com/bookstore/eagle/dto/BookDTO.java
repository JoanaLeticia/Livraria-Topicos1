package com.bookstore.eagle.dto;

import jakarta.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;

public record BookDTO (

    @NotBlank(message = "Please provide the name.")
    String name,

    @NotBlank(message = "Please provide the description.")
    String description,

    @NotBlank
    String author,

    @NotBlank
    String publisher,

    Integer genre,

    Integer rating,

    @NotNull
    Double price,

    @NotNull
    Integer stock

) {

}
