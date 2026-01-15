package com.fees.management.exception;

import java.util.List;

public class ApiErrorResponse {
    private int status;
    private String error;
    private List<String> errors;

    public ApiErrorResponse(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public ApiErrorResponse(int status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getErrors() {
        return errors;
    }
}
