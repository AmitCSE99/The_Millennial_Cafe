package com.backend.restaurant_service.controllers;

import com.backend.restaurant_service.entities.Customise;
import com.backend.restaurant_service.response_generators.ResponseGenerator;
import com.backend.restaurant_service.responses.SuccessResponse;
import com.backend.restaurant_service.services.CustomiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customise")
public class CustomiseController {

    @Autowired
    private CustomiseService customiseService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Customise>> addCustomisation(@RequestBody Customise customise){
        Customise createdCustomisation=customiseService.addCustomisation(customise);
        return new ResponseGenerator<Customise>().generateSuccessResponse(HttpStatus.CREATED,createdCustomisation);
    }

    @DeleteMapping("/{customiseId}")
    public ResponseEntity<SuccessResponse<String>> deleteCustomisation(@PathVariable String customiseId){
        customiseService.deleteCustomisation(customiseId);
        return new ResponseGenerator<String>().generateSuccessResponse(HttpStatus.OK,"The Customisation has been deleted!");
    }

}
