package org.example.springframework.exception;

public class OrderProductNotFoundException extends RuntimeException{
    public OrderProductNotFoundException(String message) {
        super(message);
    }
}