package com.datum.services.ratingservice.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        System.out.println(ex.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ErrorResponse.builder(ex, status,
                        ex.getMessage() != null ? ex.getMessage() : "Bad Request"
                )
                .title("Bad Request")
                .property("status", status.value())
                .type(URI.create(ex.getClass().getSimpleName()))
                .build();
    }
}
