package com.backend.restaurant_service.external_services;

import com.backend.restaurant_service.external_entities.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface CartService {

    @PostMapping("/cart")
    Cart addMenuToCart(@RequestBody Cart cart);
}
