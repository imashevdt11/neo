package com.imashevdt11.store.exceptions;

public class ProductNotFoundException extends BaseException{
    public ProductNotFoundException(String message, Integer status) {
        super(message, status);
    }
}