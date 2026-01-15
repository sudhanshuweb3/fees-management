package com.fees.management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiErrorResponse handleInvalidJson(HttpMessageNotReadableException ex) {

        log.error("Invalid JSON request", ex);

        return new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid or malformed JSON in request body"
        );
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ApiErrorResponse handleNoResource(NoResourceFoundException ex) {

        return new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Endpoint not found",
                List.of()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiErrorResponse handleDataIntegrity(DataIntegrityViolationException ex) {

        return new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Cannot delete student because related payments exist"
        );
    }




}
