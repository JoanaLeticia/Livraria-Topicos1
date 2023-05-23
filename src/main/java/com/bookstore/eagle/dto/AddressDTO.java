package com.bookstore.eagle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressDTO(
    boolean main,

    @NotBlank(message = "Please provide the address.")
    String street,

    @NotBlank(message = "Please provide the street number.")
    String number,
    
    String adjunct,

    @NotBlank(message = "Please provide the neighborhood.")
    String neighborhood,

    @NotBlank(message = "Please provide the ZIP code.")
    @Size(max = 8)
    String zip,

    Long cityId,

    Long clientId
) {
    
}
