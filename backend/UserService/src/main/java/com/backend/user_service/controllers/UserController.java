package com.backend.user_service.controllers;

import com.backend.user_service.entities.Role;
import com.backend.user_service.entities.User;
import com.backend.user_service.exceptions.ResourceNotFoundException;
import com.backend.user_service.repositories.RoleRepository;
import com.backend.user_service.response_generators.ResponseGenerator;
import com.backend.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user){
        Role role=roleRepository.findById("admin").orElseThrow(()->new ResourceNotFoundException("Not found"));
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        System.out.println(user);
        User createdUser=userService.createUser(user);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.CREATED,createdUser);
//        return ResponseGenerator.generateFailureResponse(HttpStatus.NOT_FOUND,"Sample");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> loginUser(@RequestBody Map<String,String> data){
        Map<String,Object> response=userService.loginUser(data);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.OK,response);
    }

    @GetMapping("/testRole")
    public ResponseEntity<String> testRole(){
        return ResponseEntity.ok().body("Admins only");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> getUser(@PathVariable String userId){
        User user=userService.getUserById(userId);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.OK,user);
    }

    @PatchMapping
    public ResponseEntity<Map<String,Object>> editUser(@RequestBody User user){
        User editedUser=userService.editUser(user);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.OK,user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.OK,"The User has been removed!");
    }
}
