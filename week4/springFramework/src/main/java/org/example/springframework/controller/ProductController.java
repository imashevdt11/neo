package org.example.springframework.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.springframework.dto.ProductDTO;
import org.example.springframework.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Validated
public class ProductController {

    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<ProductDTO>> getProductsByPrice(@PathVariable Double price) {
        List<ProductDTO> productList = productService.getProductsByPrice(price);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/price/below/{price}")
    public ResponseEntity<List<ProductDTO>> getProductsBelowPrice(@PathVariable Double price) {
        List<ProductDTO> productList = productService.getProductsBelowPrice(price);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/price/above/{price}")
    public ResponseEntity<List<ProductDTO>> getProductsAbovePrice(@PathVariable Double price) {
        List<ProductDTO> productList = productService.getProductsAbovePrice(price);
        return ResponseEntity.ok(productList);
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductDTO product, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        ProductDTO addedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable @Positive Long id,
                                                @Valid @RequestBody ProductDTO productDTO,
                                                BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        ProductDTO savedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}