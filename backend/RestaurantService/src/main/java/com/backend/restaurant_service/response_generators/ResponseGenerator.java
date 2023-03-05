package com.backend.restaurant_service.response_generators;

import com.backend.restaurant_service.responses.FailureResponse;
import com.backend.restaurant_service.responses.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseGenerator<T> {

    public ResponseEntity<SuccessResponse<T>> generateSuccessResponse(HttpStatus status, T data){
        return ResponseEntity.status(status).body(new SuccessResponse<>(true,status.value(),data));
    }

    public ResponseEntity<FailureResponse> generateFailureResponse(HttpStatus status, String message, String exceptionClass){
        return ResponseEntity.status(status).body(new FailureResponse(false,status.value(),message, exceptionClass));
    }
}
