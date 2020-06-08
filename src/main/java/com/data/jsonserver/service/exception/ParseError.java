package com.data.jsonserver.service.exception;

public class ParseError extends RuntimeException{
    public ParseError(String message) {
        super(message);
    }
}
