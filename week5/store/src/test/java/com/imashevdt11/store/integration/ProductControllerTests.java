package com.imashevdt11.store.integration;

import com.imashevdt11.store.controllers.ProductController;
import com.imashevdt11.store.dtos.ProductDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTests {

    @Autowired
    ProductController productController;

    @Test
    void testCreateProduct() {
        ProductDto product = ProductDto.builder().name("product").price(75.0).build();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        ResponseEntity<?> responseEntity = productController.createProduct(product, bindingResult);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        ProductDto createdProduct = (ProductDto) responseEntity.getBody();
        assertNotNull(createdProduct);
        Long productId = createdProduct.getId();

        productController.deleteProduct(productId);
    }

    @Test
    void testGetAllProducts() {
        ResponseEntity<List<ProductDto>> responseEntity = productController.getAllProducts();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetProductById() {
        ResponseEntity<ProductDto> responseEntity = productController.getProductById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetProductsByPrice() {
        ResponseEntity<List<ProductDto>> responseEntity = productController.getProductsByPrice(100.0);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetProductsBelowPrice() {
        ResponseEntity<List<ProductDto>> responseEntity = productController.getProductsBelowPrice(100.0);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetProductsAbovePrice() {
        ResponseEntity<List<ProductDto>> responseEntity = productController.getProductsAbovePrice(10.0);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateProduct() {

        ProductDto product = ProductDto.builder().name("product").price(75.0).build();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        ResponseEntity<?> responseEntity = productController.createProduct(product, bindingResult);

        ProductDto createdProduct = (ProductDto) responseEntity.getBody();
        assertNotNull(createdProduct);
        Long productId = createdProduct.getId();

        ProductDto updatedProduct = ProductDto.builder().name("product_updated").price(100.0).build();
        when(bindingResult.hasErrors()).thenReturn(false);
        ResponseEntity<?> responseEntity2 = productController.updateProduct(productId, updatedProduct, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());

        ProductDto updatedProductDto = (ProductDto) responseEntity2.getBody();
        assertNotNull(updatedProductDto);
        assertEquals("product_updated", updatedProductDto.getName());
        assertEquals(100.0, updatedProductDto.getPrice());

        productController.deleteProduct(productId);
    }

    @Test
    void testDeleteProductById(){
        ResponseEntity<Void> product = productController.deleteProduct(1L);
        assertEquals(HttpStatus.NO_CONTENT, product.getStatusCode());
    }
}