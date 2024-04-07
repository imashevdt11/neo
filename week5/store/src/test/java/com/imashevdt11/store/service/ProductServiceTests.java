package com.imashevdt11.store.service;

import com.imashevdt11.store.dtos.ProductDto;
import com.imashevdt11.store.entities.Product;
import com.imashevdt11.store.repositories.ProductRepository;
import com.imashevdt11.store.services.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    private ProductDto productDto;

    @BeforeEach
    public void init() {
        product = Product.builder().name("knife").price(25.0).build();
        productDto = ProductDto.builder().name("knife").price(25.0).build();
    }

    @Test
    public void ProductService_AddProduct_AddsProductDto() {

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ProductDto savedProduct = productService.createProduct(productDto);

        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    public void ProductService_GetProductById_ReturnsProductDto() {

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));

        ProductDto savedProduct = productService.getProductById(1L);

        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    public void ProductService_GetAllProducts_ReturnsListOfProducts() {

        List<Product> products = new ArrayList<>();

        products.add(product);
        products.add(product);

        when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> productList = productService.getAllProducts();

        Assertions.assertThat(productList).isNotNull();
        Assertions.assertThat(productList.size()).isEqualTo(2);
    }

    @Test
    public void ProductService_UpdateProductById_UpdatesProductDto() {

        ProductDto updatedProductDto = ProductDto.builder().name("blade").price(75.0).build();

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        when(productRepository.save(Mockito.any(Product.class))).thenAnswer(invocation -> {
            Product updatedProduct = invocation.getArgument(0);
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            return product;
        });
        ProductDto savedProduct = productService.updateProduct(1L, updatedProductDto);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct).isEqualTo(updatedProductDto);
    }

    @Test
    public void ProductService_DeleteProductById_RemovesProductFromRepository() {

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));

        productService.deleteProductById(1L);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}