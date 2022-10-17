package com.trudmin.api.exceptions;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(NoSuchElementException ex) {
        HttpStatus httpStatus =HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, ex, ex.getMessage());
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(DuplicateKeyException ex) {
        HttpStatus httpStatus =HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, ex, ex.getMessage());
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex) {
        HttpStatus httpStatus =HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, ex, ex.getMessage());
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(InvalidDataException ex) {
        HttpStatus httpStatus =HttpStatus.BAD_REQUEST;
        List<String> errors = ex.getResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
        return buildResponseEntity(httpStatus, new RuntimeException("Data enviada es invalida"), ex.getMessage(), errors);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException ex) {
        HttpStatus httpStatus =HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, new RuntimeException("Tipo de argumento invalido"), ex.getMessage());
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        HttpStatus httpStatus =HttpStatus.INTERNAL_SERVER_ERROR;
        return buildResponseEntity(httpStatus, new RuntimeException("Error inesperado, favor de contactar al administrador. "), ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception ex, String error) {
        ex.printStackTrace();
        return buildResponseEntity(httpStatus, ex,error, null);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception ex, String error, List<String> errors) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setMessage("USRMSG-" + ex.getMessage());
        errorResp.setStatus(httpStatus.value());
        errorResp.setTimestamp(new Date());
        errorResp.setError(error);
        errorResp.setErrors(errors);
        ex.printStackTrace();
        return new ResponseEntity<>(errorResp, httpStatus);
    }
}
