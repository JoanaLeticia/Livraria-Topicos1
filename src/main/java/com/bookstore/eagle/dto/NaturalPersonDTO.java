package com.bookstore.eagle.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NaturalPersonDTO (

    @NotBlank(message = "Please provide the email.")
    String email,

    @NotBlank(message = "Please provide the name.")
    String name,

    @NotBlank(message = "Please provide the CPF.")
    @Size(max = 14)
    String cpf,

    List<PhoneDTO> phones,

    List<AddressDTO> address,

    List<OrderDTO> orders

) {

}
