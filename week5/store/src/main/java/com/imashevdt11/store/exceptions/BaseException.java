package com.imashevdt11.store.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {

    private Integer status;

    public BaseException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}