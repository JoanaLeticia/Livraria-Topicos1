package com.bookstore.eagle.dto;

import java.util.List;

import com.bookstore.eagle.model.Address;
import com.bookstore.eagle.model.Order;
import com.bookstore.eagle.model.Product;
import com.bookstore.eagle.model.Phone;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO (

    @NotBlank(message = "Please provide your email.")
    String email,

    List<Phone> phone,

    List<Address> addresses,

    List<Order> orders,
    
    List<Product> products

) {

}
