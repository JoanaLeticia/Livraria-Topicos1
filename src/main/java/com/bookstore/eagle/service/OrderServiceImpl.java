package com.bookstore.eagle.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bookstore.eagle.dto.OrderDTO;
import com.bookstore.eagle.dto.OrderResponseDTO;
import com.bookstore.eagle.model.Order;
import com.bookstore.eagle.model.OrderStatus;
import com.bookstore.eagle.repository.OrderRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    Validator validator;

    @Override
    public List<OrderResponseDTO> listOrders() {
        List<Order> list = orderRepository.listAll();
        return list.stream().map(OrderResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO searchOrderById(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null)
            throw new NotFoundException("The order was not found.");
        return new OrderResponseDTO(order);
    }

    @Override
    @Transactional
    public OrderResponseDTO addOrder(OrderDTO orderDTO) throws ConstraintViolationException {
        validating(orderDTO);

        Order entity = new Order();
        entity.setStatus(OrderStatus.valueOf(orderDTO.status()));
        entity.setTotalQuantity(orderDTO.totalQuantity());
        entity.setTotalValue(orderDTO.totalValue());
        orderRepository.persist(entity);

        return new OrderResponseDTO(entity);

    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderDTO orderDTO) throws ConstraintViolationException {
        validating(orderDTO);

        Order entity = orderRepository.findById(id);

        entity.setStatus(OrderStatus.valueOf(orderDTO.status()));
        entity.setTotalQuantity(orderDTO.totalQuantity());
        entity.setTotalValue(orderDTO.totalValue());
        orderRepository.persist(entity);
        
        return new OrderResponseDTO(entity);
    }

    private void validating(OrderDTO orderDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<OrderDTO>> violations = validator.validate(orderDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
