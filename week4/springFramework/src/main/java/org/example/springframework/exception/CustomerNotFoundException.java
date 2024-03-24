package org.example.springframework.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BaseException {
    public CustomerNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}