package com.backend.restaurant_service.response_generators;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseGenerator {

    public static ResponseEntity<Map<String,Object>> generateSuccessResponse(HttpStatus status, Object data){
        Map<String,Object> responseMap=new HashMap<>();
        responseMap.put("success",true);
        responseMap.put("data",data);
        responseMap.put("status",status.value());

        return ResponseEntity.status(status).body(responseMap);
    }

    public static ResponseEntity<Map<String,Object>> generateFailureResponse(HttpStatus status,String message){
        Map<String,Object> responseMap=new HashMap<>();
        responseMap.put("success",false);
        responseMap.put("error",message);
        responseMap.put("status",status.value());

        return ResponseEntity.status(status).body(responseMap);
    }
}
