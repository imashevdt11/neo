package org.example.springframework.service;

import org.example.springframework.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    List<ProductDTO> getProductsByPrice(Double price);

    List<ProductDTO> getProductsBelowPrice(Double price);

    List<ProductDTO> getProductsAbovePrice(Double price);

    ProductDTO addProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}