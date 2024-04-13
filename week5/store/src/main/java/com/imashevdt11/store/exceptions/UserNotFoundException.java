package com.imashevdt11.store.exceptions;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message, Integer status) {
        super(message, status);
    }
}