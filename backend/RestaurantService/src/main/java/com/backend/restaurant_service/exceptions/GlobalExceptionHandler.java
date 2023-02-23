package com.backend.restaurant_service.exceptions;

import com.backend.restaurant_service.response_generators.ResponseGenerator;
import com.backend.restaurant_service.responses.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<FailureResponse> resourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseGenerator<>().generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<FailureResponse> elementAlreadyExistsException(ElementAlreadyExistsException elementAlreadyExistsException){
        return new ResponseGenerator<>().generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, elementAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<FailureResponse> entityNotFoundException(EntityNotFoundException entityNotFoundException){
        return new ResponseGenerator<>().generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,entityNotFoundException.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<FailureResponse> sqlIntegrityException(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        return new ResponseGenerator<>().generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,sqlIntegrityConstraintViolationException.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<FailureResponse> constraintViolationException(ConstraintViolationException constraintViolationException){
        return new ResponseGenerator<>().generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,constraintViolationException.getMessage());
    }
}
