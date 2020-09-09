package com.data.mockapiserver.service.file.exception;

public class ParseError extends RuntimeException{
    public ParseError(String message) {
        super(message);
    }
}
