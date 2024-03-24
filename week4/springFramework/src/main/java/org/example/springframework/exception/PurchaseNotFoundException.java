package org.example.springframework.exception;

import org.springframework.http.HttpStatus;

public class PurchaseNotFoundException extends BaseException{
    public PurchaseNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}