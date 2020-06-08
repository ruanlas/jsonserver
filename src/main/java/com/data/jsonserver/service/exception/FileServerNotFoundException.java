package com.data.jsonserver.service.exception;

public class FileServerNotFoundException extends RuntimeException{
    public FileServerNotFoundException(String message) {
        super(message);
    }
}
