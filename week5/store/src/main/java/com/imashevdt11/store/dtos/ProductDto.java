package com.imashevdt11.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 51)
    String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    Double price;
}