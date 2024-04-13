package com.imashevdt11.store.unit.product;

import com.imashevdt11.store.entities.Product;
import com.imashevdt11.store.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest(properties = "spring.profiles.active=test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void init() {
        product = Product.builder().name("knife").price(25.0).build();
    }

    @Test
    public void ProductRepository_Save_ReturnSavedProduct() {

        Product savedProduct = productRepository.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    public void ProductRepository_FindAll_ReturnIsNotNull() {

        Product product2 = Product.builder().name("knife").price(25.0).build();
        productRepository.save(product);
        productRepository.save(product2);

        List<Product> productList = productRepository.findAll();

        Assertions.assertThat(productList).isNotNull();
        Assertions.assertThat(productList.size()).isEqualTo(2);
    }

    @Test
    public void ProductRepository_FindById_ReturnProduct() {

        productRepository.save(product);

        Product soughtForProduct = productRepository.findById(product.getId()).get();

        Assertions.assertThat(soughtForProduct).isNotNull();
    }

    @Test
    public void ProductRepository_FindByPrice_ReturnProductIsEqualTo() {

        productRepository.save(product);
        productRepository.save(product);

        List<Product> productList = productRepository.findByPrice(25.0);

        Assertions.assertThat(productList.size()).isEqualTo(1);
    }

    @Test
    public void ProductRepository_UpdateProduct_ReturnProductIsEqualTo() {

        productRepository.save(product);

        Product productSave = productRepository.findById(product.getId()).get();
        product.setName("blade");
        product.setPrice(30.0);

        Product updatedProduct = productRepository.save(productSave);

        Assertions.assertThat(updatedProduct.getName()).isEqualTo("blade");
        Assertions.assertThat(updatedProduct.getPrice()).isEqualTo(30.0);
    }

    @Test
    public void ProductRepository_DeleteById_ReturnProductIsEmpty() {

        productRepository.save(product);

        productRepository.deleteById(product.getId());
        Optional<Product> productReturn = productRepository.findById(product.getId());

        Assertions.assertThat(productReturn).isEmpty();
    }
}
