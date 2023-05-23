package com.bookstore.eagle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PhoneDTO (
    Long id,

    @NotBlank(message = "Please provide the DDD.")
    @Size(max = 2)
    Integer ddd,

    @NotBlank(message = "Please provide the number.")
    @Size(max = 9)
    String number

) {    
}
