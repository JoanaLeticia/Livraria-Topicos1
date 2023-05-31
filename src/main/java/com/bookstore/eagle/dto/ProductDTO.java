package com.bookstore.eagle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductDTO (

    @NotBlank(message = "Please provide the name.")
    @Size(max = 100)
    String name,

    @NotBlank(message = "Please provide the description.")
    @Size(max = 500)
    String description,

    String imageName,

    Double price,

    Integer stock
){
    
}
