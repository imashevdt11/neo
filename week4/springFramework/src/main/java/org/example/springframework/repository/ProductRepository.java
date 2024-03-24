package org.example.springframework.repository;

import org.example.springframework.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByPrice(Double price);
    List<Product> findProductsByPriceIsLessThan(Double price);
    List<Product> findProductsByPriceIsGreaterThan(Double price);
}