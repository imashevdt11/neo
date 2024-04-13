package com.imashevdt11.store.controllers;

import com.imashevdt11.store.commons.EndpointConstants;
import com.imashevdt11.store.dtos.ProductDto;
import com.imashevdt11.store.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(EndpointConstants.PRODUCTS_ENDPOINT)
@Tag(name = "Product")
public class ProductController {

    private final ProductService productService;

    @Operation(
            description = "Create a new product",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to validation error"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto product, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        ProductDto addedProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    @GetMapping
    @Operation(
            description = "Get all products",
            responses = @ApiResponse(responseCode = "200", description = "List of products retrieved successfully")
    )
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @Operation(
            description = "Get product by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @Operation(
            description = "Get products by price",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of products retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "No products found with specified price"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/price/{price}")
    public ResponseEntity<List<ProductDto>> getProductsByPrice(@PathVariable Double price) {
        List<ProductDto> productList = productService.getProductsByPrice(price);
        return ResponseEntity.ok(productList);
    }

    @Operation(
            description = "Get products below a certain price",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of products retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "No products found below the specified price"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/price/below/{price}")
    public ResponseEntity<List<ProductDto>> getProductsBelowPrice(@PathVariable Double price) {
        List<ProductDto> productList = productService.getProductsBelowPrice(price);
        return ResponseEntity.ok(productList);
    }

    @Operation(
            description = "Get products above a certain price",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of products retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "No products found above the specified price"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/price/above/{price}")
    public ResponseEntity<List<ProductDto>> getProductsAbovePrice(@PathVariable Double price) {
        List<ProductDto> productList = productService.getProductsAbovePrice(price);
        return ResponseEntity.ok(productList);
    }

    @Operation(
            description = "Update product by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to validation error"),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable @Positive Long id, @Valid @RequestBody ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        ProductDto savedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @Operation(
            description = "Delete product by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}