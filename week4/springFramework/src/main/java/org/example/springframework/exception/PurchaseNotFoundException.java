package org.example.springframework.exception;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException(String message) {
        super(message);
    }
}