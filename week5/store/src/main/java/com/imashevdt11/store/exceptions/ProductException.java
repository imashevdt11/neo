package com.imashevdt11.store.exceptions;

public class ProductException extends BaseException {

    public ProductException(String message, Integer status) {
        super(status, message);
    }
}