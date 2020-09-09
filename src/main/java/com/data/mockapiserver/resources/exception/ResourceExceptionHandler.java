package com.data.mockapiserver.resources.exception;

import com.data.mockapiserver.service.router.exception.PathNotFoundRuntimeException;
import com.data.mockapiserver.service.router.exception.RouterNotFoundRuntimeException;
import com.data.mockapiserver.service.store.exception.DataNotFoundRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(PathNotFoundRuntimeException.class)
    public ResponseEntity<DefaultError> pathNotFound(
            PathNotFoundRuntimeException e, HttpServletRequest httpServletRequest){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        DefaultError defaultError = DefaultError.builder()
                .error(notFound.name())
                .timestamp(System.currentTimeMillis())
                .path(httpServletRequest.getRequestURI())
                .status(notFound.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity
                .status(notFound)
                .body(defaultError);
    }

    @ExceptionHandler(DataNotFoundRuntimeException.class)
    public ResponseEntity<DefaultError> dataNotFound(
            DataNotFoundRuntimeException e, HttpServletRequest httpServletRequest){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        DefaultError defaultError = DefaultError.builder()
                .error(notFound.name())
                .timestamp(System.currentTimeMillis())
                .path(httpServletRequest.getRequestURI())
                .status(notFound.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity
                .status(notFound)
                .body(defaultError);
    }

    @ExceptionHandler(RouterNotFoundRuntimeException.class)
    public ResponseEntity<DefaultError> routerNotFound(
            RouterNotFoundRuntimeException e, HttpServletRequest httpServletRequest){
        HttpStatus httpStatus = e.getHttpStatus();
        DefaultError defaultError = DefaultError.builder()
                .error(httpStatus.name())
                .timestamp(System.currentTimeMillis())
                .path(httpServletRequest.getRequestURI())
                .status(httpStatus.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity
                .status(httpStatus)
                .body(defaultError);
    }
}
