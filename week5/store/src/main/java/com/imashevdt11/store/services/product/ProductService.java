package com.imashevdt11.store.services.product;

import com.imashevdt11.store.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    List<ProductDto> getProductsByPrice(Double price);

    List<ProductDto> getProductsBelowPrice(Double price);

    List<ProductDto> getProductsAbovePrice(Double price);

    ProductDto addProduct(ProductDto productDto);

    ProductDto updateProduct(Long id, ProductDto productDto);

    void deleteProductById(Long id);
}