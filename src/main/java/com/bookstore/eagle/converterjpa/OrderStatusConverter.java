package com.bookstore.eagle.converterjpa;

import com.bookstore.eagle.model.OrderStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus == null ? null : orderStatus.getId();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer id) {
        return OrderStatus.valueOf(id);
    }
}
