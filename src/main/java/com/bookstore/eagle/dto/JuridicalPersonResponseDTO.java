package com.bookstore.eagle.dto;

import java.util.List;

import com.bookstore.eagle.model.Order;
import com.bookstore.eagle.model.JuridicalPerson;
import com.bookstore.eagle.model.Product;

public record JuridicalPersonResponseDTO (
    Long id,
    String name,
    String cnpj,
    String email,
    List<Order> orders,
    List<Product> wishList
){
    public JuridicalPersonResponseDTO(JuridicalPerson jp) {
        this(jp.getId(),
        jp.getName(),
        jp.getCnpj(),
        jp.getEmail(),
        jp.getOrders(),
        jp.getWishLists());
    }
}
