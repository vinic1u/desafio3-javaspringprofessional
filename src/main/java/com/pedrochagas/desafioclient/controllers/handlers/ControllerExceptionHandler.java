package com.pedrochagas.desafioclient.controllers.handlers;

import com.pedrochagas.desafioclient.dtos.CustomError;
import com.pedrochagas.desafioclient.dtos.CustomFieldError;
import com.pedrochagas.desafioclient.dtos.FieldErrorMessage;
import com.pedrochagas.desafioclient.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(
                Instant.now(),
                status.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomFieldError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomFieldError customFieldError = new CustomFieldError(
                Instant.now(),
                status.value(),
                "Informe valores validos!",
                request.getRequestURI()
        );
        for(FieldError fieldError: exception.getFieldErrors()){
            customFieldError.addError(new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ResponseEntity.status(status).body(customFieldError);
    }
}
