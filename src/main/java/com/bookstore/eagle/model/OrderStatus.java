package com.bookstore.eagle.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatus {
    WAITING_PAYMENT(1, "Waiting for Payment"),
    PROCESSING(2, "Processing"),
    SHIPPED(3, "Shipped"),
    DELIVERED(4, "Delivered"),
    CANCELLED(5, "Cancelled");

    private int id;

    private String label;
    
    private OrderStatus(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static OrderStatus valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (id.equals(orderStatus.getId()))
                return orderStatus;
        }
        throw new IllegalArgumentException("invalid id:" + id);
    }
}
