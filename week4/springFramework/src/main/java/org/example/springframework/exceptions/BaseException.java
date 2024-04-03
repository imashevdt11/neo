package org.example.springframework.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {

    private Integer status;

    public BaseException(Integer status, String message) {
        super(message);
        this.status = status;
    }
}