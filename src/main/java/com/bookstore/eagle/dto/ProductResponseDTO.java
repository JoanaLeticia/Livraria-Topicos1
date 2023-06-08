package com.bookstore.eagle.dto;

import java.util.List;

import com.bookstore.eagle.model.RequestedItem;
import com.bookstore.eagle.model.Product;

public record ProductResponseDTO (
    Long id,
    String name,
    String description,
    String imageName,
    Double price,
    Integer stock,
    List<RequestedItem> requestedItems

) {

    public ProductResponseDTO(Product product) {
        this(product.getId(),
        product.getName(),
        product.getDescription(),
        product.getImageName(),
        product.getPrice(),
        product.getStock(), 
        product.getRequestedItems());
    }

    public static ProductResponseDTO valueOf(Product p) {
        if (p.getId() == null)
            return new ProductResponseDTO(p.getId(),
                p.getName(),
                p.getDescription(),
                p.getImageName(),
                p.getPrice(),
                p.getStock(),
                null);
        
                return new ProductResponseDTO(p.getId(),
                p.getName(),
                p.getDescription(),
                p.getImageName(),
                p.getPrice(),
                p.getStock(),
                null);
    }

}
