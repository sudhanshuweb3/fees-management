package com.fees.management.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleValidation(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return new ApiErrorResponse(400, errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorResponse handleNotFound(ResourceNotFoundException ex) {
        return new ApiErrorResponse(404, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleGeneric(Exception ex) {
        return new ApiErrorResponse(500, "Internal server error");
    }
}
