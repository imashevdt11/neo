package com.imashevdt11.store.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

    private Integer status;

    private String message;

    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}