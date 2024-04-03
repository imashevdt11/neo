package org.example.springframework.exceptions;

public class ProductException extends BaseException {

    public ProductException(String message, Integer status) {
        super(status, message);
    }
}