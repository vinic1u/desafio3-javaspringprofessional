package com.pedrochagas.desafioclient.dtos;

import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CustomFieldError extends CustomError{

    private List<FieldErrorMessage> errors = new ArrayList<>();

    public CustomFieldError(List<FieldErrorMessage> errors) {
        this.errors = errors;
    }

    public CustomFieldError(Instant timestamp, Integer status, String error, String path, List<FieldErrorMessage> errors) {
        super(timestamp, status, error, path);
        this.errors = errors;
    }

    public CustomFieldError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldErrorMessage> getErrors() {
        return errors;
    }

    public void addError(FieldErrorMessage error){
        errors.add(error);
    }
}
