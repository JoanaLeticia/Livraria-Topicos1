package com.bookstore.eagle.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bookstore.eagle.dto.ProductDTO;
import com.bookstore.eagle.dto.ProductResponseDTO;
import com.bookstore.eagle.model.Product;
import com.bookstore.eagle.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {
    @Inject
    ProductRepository productRepository;

    @Inject
    Validator validator;

    @Override
    public List<ProductResponseDTO> listProducts() {
        List<Product> list = productRepository.listAll();
        return list.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO searchProductById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null)
            throw new NotFoundException("The product was not found.");
        return new ProductResponseDTO(product);
    }

    @Override
    @Transactional
    public ProductResponseDTO addProduct(ProductDTO productDTO) throws ConstraintViolationException {
        validating(productDTO);

        Product entity = new Product();
        entity.setName(productDTO.name());
        entity.setDescription(productDTO.description());
        entity.setPrice(productDTO.price());
        entity.setStock(productDTO.stock());
        productRepository.persist(entity);

        return new ProductResponseDTO(entity);

    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductDTO productDTO) throws ConstraintViolationException {
        validating(productDTO);

        Product entity = productRepository.findById(id);

        entity.setName(productDTO.name());
        entity.setDescription(productDTO.description());
        entity.setPrice(productDTO.price());
        entity.setStock(productDTO.stock());
        
        return new ProductResponseDTO(entity);
    }

    private void validating(ProductDTO productDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
