package com.backend.user_service.exceptions;

import com.backend.user_service.response_generators.ResponseGenerator;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> resourceNotFound(ResourceNotFoundException exception){
        return ResponseGenerator.generateFailureResponse(HttpStatus.NOT_FOUND,exception.getMessage());
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<Map<String,Object>> propertyValueException(PropertyValueException exception){
        System.out.println(exception.getPropertyName());
        return ResponseGenerator.generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String,Object>> sqlException(SQLException exception){
        System.out.println("In SQL exception");
        return ResponseGenerator.generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public  ResponseEntity<Map<String,Object>> propertyValueException(ConstraintViolationException exception){
        System.out.println(exception.getConstraintViolations());
        return ResponseGenerator.generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
    }
    @ExceptionHandler(InvalidDataException.class)
    public  ResponseEntity<Map<String,Object>> invalidDataException(InvalidDataException exception){
        return ResponseGenerator.generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
    }
}
