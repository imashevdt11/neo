package com.imashevdt11.store.services;

import com.imashevdt11.store.dtos.ProductDto;
import com.imashevdt11.store.entities.Product;
import com.imashevdt11.store.exceptions.ProductNotFoundException;
import com.imashevdt11.store.repositories.ProductRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
        Product savedProduct = productRepository.save(product);
        return convertToProductDto(savedProduct);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::convertToProductDto).toList();
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return convertToProductDto(product);
    }

    public List<ProductDto> getProductsByPrice(Double price) {
        List<Product> productList = productRepository.findByPrice(price);

        if (productList.isEmpty()) {
            throw new ProductNotFoundException("Products with a price equal to " + price + " not found", HttpStatus.NOT_FOUND.value());
        }
        return productList.stream().map(this::convertToProductDto).toList();
    }

    public List<ProductDto> getProductsBelowPrice(Double price) {
        List<Product> productList = productRepository.findProductsByPriceIsLessThan(price);

        if (productList.isEmpty()) {
            throw new ProductNotFoundException("Products with a price below " + price + " not found", HttpStatus.NOT_FOUND.value());
        }
        return productList.stream().map(this::convertToProductDto).toList();
    }

    public List<ProductDto> getProductsAbovePrice(Double price) {
        List<Product> productList = productRepository.findProductsByPriceIsGreaterThan(price);

        if (productList.isEmpty()) {
            throw new ProductNotFoundException("Products with a price above " + price + " not found", HttpStatus.NOT_FOUND.value());
        }
        return productList.stream().map(this::convertToProductDto).toList();
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {

        ProductDto product = getProductById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        Product updatedProduct = productRepository.save(convertToProductEntity(product));

        return convertToProductDto(updatedProduct);
    }

    public void deleteProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }
    }

    private ProductDto convertToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    private Product convertToProductEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
    }
}