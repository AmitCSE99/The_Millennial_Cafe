package com.backend.restaurant_service.controllers;

import com.backend.restaurant_service.entities.Customise;
import com.backend.restaurant_service.services.CustomiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customise")
public class CustomiseController {

    @Autowired
    private CustomiseService customiseService;

    @PostMapping
    public ResponseEntity<Customise> addCustomisation(@RequestBody Customise customise){
        return ResponseEntity.ok(customiseService.addCustomisation(customise));
    }

    @DeleteMapping("/{customiseId}")
    public ResponseEntity<String> deleteCustomisation(@PathVariable String customiseId){
        customiseService.deleteCustomisation(customiseId);
        return ResponseEntity.ok("deleted");
    }

}
