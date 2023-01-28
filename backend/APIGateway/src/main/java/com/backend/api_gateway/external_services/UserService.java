package com.backend.api_gateway.external_services;

import com.backend.api_gateway.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@ReactiveFeignClient(name = "USER-SERVICE")
public interface UserService {

    @GetMapping("/user/{userId}")
    Mono<Map<String,Object>> getUser(@PathVariable String userId);

    @PostMapping("/user")
    Mono<Map<String,Object>> createUser(@RequestBody User user);

}
