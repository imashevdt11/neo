package org.example.springframework.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 51)
    String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    Double price;
}