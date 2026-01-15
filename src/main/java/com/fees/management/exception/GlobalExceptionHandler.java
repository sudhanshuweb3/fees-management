package com.fees.management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleValidation(MethodArgumentNotValidException ex) {

        log.warn("Validation failed: {}", ex.getMessage());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return new ApiErrorResponse(400, errors);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorResponse handleNotFound(ResourceNotFoundException ex) {

        log.error("Resource not found: {}", ex.getMessage());

        return new ApiErrorResponse(404, ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleGeneric(Exception ex) {

        log.error("Unexpected error occurred", ex);

        return new ApiErrorResponse(500, "Internal server error");
    }

}
