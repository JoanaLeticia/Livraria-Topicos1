package com.bookstore.eagle.dto;

import java.util.List;

import com.bookstore.eagle.model.Order;
import com.bookstore.eagle.model.NaturalPerson;

public record NaturalPersonResponseDTO (
    Long id,
    String name,
    String cpf,
    String email,
    List<Order> orders
){
    public NaturalPersonResponseDTO(NaturalPerson np) {
        this(np.getId(),
        np.getName(),
        np.getCpf(),
        np.getEmail(),
        np.getOrders());
    }
}
