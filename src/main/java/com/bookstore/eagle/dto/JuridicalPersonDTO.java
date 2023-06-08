package com.bookstore.eagle.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JuridicalPersonDTO (

    @NotBlank(message = "Please provide the email.")
    String email,

    @NotBlank(message = "Please provide the CNPJ.")
    @Size(max = 14)
    String cnpj,

    @NotBlank(message = "Please provide the name.")
    String name,

    List<PhoneDTO> phones,

    List<AddressDTO> addresses,

    List<OrderDTO> orders
)
{
}
