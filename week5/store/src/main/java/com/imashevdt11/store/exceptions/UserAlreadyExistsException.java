package com.imashevdt11.store.exceptions;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException(String message, Integer status) {
        super(message, status);
    }
}