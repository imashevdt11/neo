package org.example.springframework.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}