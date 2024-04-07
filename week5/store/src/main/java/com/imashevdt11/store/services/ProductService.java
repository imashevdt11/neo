package com.imashevdt11.store.services;

import com.imashevdt11.store.dtos.ProductDto;
import com.imashevdt11.store.entities.Product;
import com.imashevdt11.store.exceptions.ProductException;
import com.imashevdt11.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repo;

    public List<ProductDto> getAllProducts() {
        List<Product> productList = repo.findAll();
        return productList.stream().map(this::convertToProductDto).toList();
    }

    public ProductDto getProductById(Long id) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return convertToProductDto(product);
    }

    public List<ProductDto> getProductsByPrice(Double price) {
        List<Product> productList = repo.findByPrice(price);

        if (productList.isEmpty()) {
            throw new ProductException("Products with a price equal to " + price + " not found", HttpStatus.NOT_FOUND.value());
        }
        return productList.stream().map(this::convertToProductDto).toList();
    }

    public List<ProductDto> getProductsBelowPrice(Double price) {
        List<Product> productList = repo.findProductsByPriceIsLessThan(price);
        if (productList.isEmpty()) {
            throw new ProductException("Products with a price below " + price + " not found", HttpStatus.NOT_FOUND.value());
        }
        return productList.stream().map(this::convertToProductDto).toList();
    }

    public List<ProductDto> getProductsAbovePrice(Double price) {
        List<Product> productList = repo.findProductsByPriceIsGreaterThan(price);
        if (productList.isEmpty()) {
            throw new ProductException("Products with a price above " + price + " not found", HttpStatus.NOT_FOUND.value());
        }
        return productList.stream().map(this::convertToProductDto).toList();
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        Product savedProduct = repo.save(product);
        return convertToProductDto(savedProduct);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        ProductDto existingProductDto = getProductById(id);
        if (existingProductDto == null) {
            throw new ProductException("Product not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }

        existingProductDto.setName(productDto.getName());
        existingProductDto.setPrice(productDto.getPrice());

        Product updatedProduct = repo.save(convertToProductEntity(existingProductDto));

        return convertToProductDto(updatedProduct);
    }

    public void deleteProductById(Long id) {
        Optional<Product> optionalProduct = repo.findById(id);
        if (optionalProduct.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new ProductException("Product not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }
    }

    private ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    private Product convertToProductEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return product;
    }
}