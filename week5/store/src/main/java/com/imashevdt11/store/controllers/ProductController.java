package com.imashevdt11.store.controllers;


import com.imashevdt11.store.dtos.ProductDto;
import com.imashevdt11.store.services.product.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<ProductDto>> getProductsByPrice(@PathVariable Double price) {
        List<ProductDto> productList = productService.getProductsByPrice(price);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/price/below/{price}")
    public ResponseEntity<List<ProductDto>> getProductsBelowPrice(@PathVariable Double price) {
        List<ProductDto> productList = productService.getProductsBelowPrice(price);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/price/above/{price}")
    public ResponseEntity<List<ProductDto>> getProductsAbovePrice(@PathVariable Double price) {
        List<ProductDto> productList = productService.getProductsAbovePrice(price);
        return ResponseEntity.ok(productList);
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductDto product, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        ProductDto addedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable @Positive Long id, @Valid @RequestBody ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        ProductDto savedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}