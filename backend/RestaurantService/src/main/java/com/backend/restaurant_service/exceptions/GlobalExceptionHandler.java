package com.backend.restaurant_service.exceptions;

import com.backend.restaurant_service.response_generators.ResponseGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> resourceNotFoundException(ResourceNotFoundException exception){
        return ResponseGenerator.generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<Map<String,Object>> elementAlreadyExistsException(ElementAlreadyExistsException elementAlreadyExistsException){
        return ResponseGenerator.generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, elementAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,Object>> entityNotFoundException(EntityNotFoundException entityNotFoundException){
        return ResponseGenerator.generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,entityNotFoundException.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Map<String,Object>> sqlIntegrityException(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        return ResponseGenerator.generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,sqlIntegrityConstraintViolationException.getMessage());
    }
}
