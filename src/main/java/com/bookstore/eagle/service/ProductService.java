package com.bookstore.eagle.service;

import java.util.List;

import com.bookstore.eagle.dto.ProductDTO;
import com.bookstore.eagle.dto.ProductResponseDTO;

public interface ProductService {

    List<ProductResponseDTO> listProducts();

    ProductResponseDTO searchProductById(Long id);

    ProductResponseDTO addProduct(ProductDTO productDTO);

    ProductResponseDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}