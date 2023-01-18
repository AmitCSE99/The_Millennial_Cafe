package com.backend.user_service.controllers;

import com.backend.user_service.entities.User;
import com.backend.user_service.response_generators.ResponseGenerator;
import com.backend.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user){
        User createdUser=userService.createUser(user);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.CREATED,createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> loginUser(@RequestBody Map<String,String> data){
        User user=userService.loginUser(data);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.OK,user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> getUser(@PathVariable String userId){
        User user=userService.getUserById(userId);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.OK,user);
    }
}
