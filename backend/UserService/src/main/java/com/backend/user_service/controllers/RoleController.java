package com.backend.user_service.controllers;

import com.backend.user_service.entities.Role;
import com.backend.user_service.response_generators.ResponseGenerator;
import com.backend.user_service.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Map<String,Object>> createRole(@RequestBody Role role){
        Role createdRole=roleService.createRole(role);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.CREATED,createdRole);
    }
}
