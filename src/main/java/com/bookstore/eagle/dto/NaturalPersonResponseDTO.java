package com.bookstore.eagle.dto;

import java.util.List;

import com.bookstore.eagle.model.Order;
import com.bookstore.eagle.model.NaturalPerson;
import com.bookstore.eagle.model.Product;

public record NaturalPersonResponseDTO (
    Long id,
    String name,
    String cpf,
    String email,
    List<Order> orders,
    List<Product> wishList
){
    public NaturalPersonResponseDTO(NaturalPerson np) {
        this(np.getId(),
        np.getName(),
        np.getCpf(),
        np.getEmail(),
        np.getOrders(),
        np.getWishLists());
    }
}
