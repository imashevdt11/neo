package com.imashevdt11.store.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private Integer status;

    private String message;
}