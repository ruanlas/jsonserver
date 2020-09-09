package com.data.mockapiserver.service.exception;

public class Error extends RuntimeException{
    public Error(String message) {
        super(message);
    }

    public Error() {
    }
}
