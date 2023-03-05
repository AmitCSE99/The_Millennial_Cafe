package com.backend.restaurant_service.exceptions;

import com.backend.restaurant_service.response_generators.ResponseGenerator;
import com.backend.restaurant_service.responses.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FailureResponse> exception(Exception exception){
        return new ResponseGenerator<>().generateFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,exception.getClass().toGenericString(), exception.getMessage());
    }
}
