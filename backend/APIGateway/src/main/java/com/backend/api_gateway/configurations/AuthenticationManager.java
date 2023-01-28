package com.backend.api_gateway.configurations;

import com.backend.api_gateway.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    private JWTUtils jwtUtil;

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username = jwtUtil.getUsernameFromToken(authToken);
        return Mono.just(jwtUtil.validateToken(authToken))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .map(valid -> {
                    Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
                    List<LinkedHashMap<String,String>> rolesMap = claims.get("role", List.class);
                    //LinkedHashMap<String,String> map =rolesMap.get(0);
                    System.out.println(rolesMap.get(0).get("authority"));
                    List<String> newRolesMap=new ArrayList<>();
                    newRolesMap.add(rolesMap.get(0).get("authority"));
                    return new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            newRolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())

                    );
                });
    }
}
