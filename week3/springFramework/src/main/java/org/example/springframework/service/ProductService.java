package org.example.springframework.service;

import org.example.springframework.entity.Product;
import org.example.springframework.exception.ProductNotFoundException;
import org.example.springframework.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOptional = getProductById(id);
        if (existingProductOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }

        Product existingProduct = existingProductOptional.get();
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
