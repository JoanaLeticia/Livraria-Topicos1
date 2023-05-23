package com.bookstore.eagle.dto;

import java.util.Date;

import com.bookstore.eagle.model.Order;
import com.bookstore.eagle.model.OrderStatus;

public record OrderResponseDTO(
    Long id,
    Integer totalQuantity,
    Double totalValue,
    Date date,
    OrderStatus status
) {
    public OrderResponseDTO(Order order) {
        this(order.getId(),
        order.getTotalQuantity(),
        order.getTotalValue(),
        order.getDate(),
        order.getStatus());
    }
}
