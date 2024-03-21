package org.example.springframework.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.springframework.dto.ProductDTO;
import org.example.springframework.entity.Product;
import org.example.springframework.exception.ProductNotFoundException;
import org.example.springframework.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository repo;

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = repo.findAll();
        return productList.stream().map(this::convertToProductDTO).toList();
    }

    public ProductDTO getProductById(Long id) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        return convertToProductDTO(product);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        Product savedProduct = repo.save(product);
        return convertToProductDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        ProductDTO existingProductDTO = getProductById(id);
        if (existingProductDTO == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }

        existingProductDTO.setName(productDTO.getName());
        existingProductDTO.setPrice(productDTO.getPrice());

        Product updatedProduct = repo.save(convertToProductEntity(existingProductDTO));

        return convertToProductDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    private Product convertToProductEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}