package com.backend.user_service.controllers;

import com.backend.user_service.entities.Address;
import com.backend.user_service.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(address));
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddressByUser(){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressByUserId("7acc0cba-3234-439c-a22c-6588c39b163c"));
    }
}
