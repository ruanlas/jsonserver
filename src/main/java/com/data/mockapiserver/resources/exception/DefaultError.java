package com.data.mockapiserver.resources.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultError {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    @Builder
    public DefaultError(Long timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
