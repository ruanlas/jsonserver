package com.data.mockapiserver.service.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundRuntimeException extends RuntimeException{
    public DataNotFoundRuntimeException(String message) {
        super(message);
    }
}
