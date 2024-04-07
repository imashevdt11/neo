package com.imashevdt11.store.repositories;

import com.imashevdt11.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPrice(Double price);

    List<Product> findProductsByPriceIsLessThan(Double price);

    List<Product> findProductsByPriceIsGreaterThan(Double price);
}