package org.example.springframework.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.springframework.entity.Customer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrdersDTO {
    Long id;
    Customer customer;
}