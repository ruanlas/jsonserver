package com.data.jsonserver.service.exception;

public class Error extends RuntimeException{
    public Error(String message) {
        super(message);
    }

    public Error() {
    }
}
