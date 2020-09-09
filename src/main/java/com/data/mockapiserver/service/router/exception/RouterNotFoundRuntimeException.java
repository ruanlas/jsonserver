package com.data.mockapiserver.service.router.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RouterNotFoundRuntimeException extends RuntimeException{
    private HttpStatus httpStatus;
    public RouterNotFoundRuntimeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
