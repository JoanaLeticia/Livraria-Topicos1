package com.bookstore.eagle.service;

import java.util.List;

import com.bookstore.eagle.dto.OrderDTO;
import com.bookstore.eagle.dto.OrderResponseDTO;

public interface OrderService {

    List<OrderResponseDTO> listOrders();

    OrderResponseDTO searchOrderById(Long id);

    OrderResponseDTO addOrder(OrderDTO orderDTO);

    OrderResponseDTO updateOrder(Long id, OrderDTO orderDTO);

    void deleteOrder(Long id);
}