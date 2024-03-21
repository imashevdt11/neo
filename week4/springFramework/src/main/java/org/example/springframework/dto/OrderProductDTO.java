package org.example.springframework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.springframework.entity.Orders;
import org.example.springframework.entity.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderProductDTO {
    Long id;
    Orders order;
    Product product;
    Integer quantity;
}