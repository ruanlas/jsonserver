package com.data.mockapiserver.service.file.exception;

public class PathNotDefinedRuntimeException extends RuntimeException {
    public PathNotDefinedRuntimeException(String message) {
        super(message);
    }
}
