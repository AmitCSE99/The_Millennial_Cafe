package com.backend.user_service.controllers;

import com.backend.user_service.entities.Cart;
import com.backend.user_service.response_generators.ResponseGenerator;
import com.backend.user_service.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Map<String,Object>> addMenuToCart(@RequestBody Cart cart){

        Cart createdCart=cartService.addToCart(cart);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.CREATED,createdCart);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Map<String,Object>> removeItemFromCart(@PathVariable String cartId){
        cartService.removeFromCart(cartId);
        return ResponseGenerator.generateSuccessResponse(HttpStatus.OK,"The Item has been removed!");
    }
}
