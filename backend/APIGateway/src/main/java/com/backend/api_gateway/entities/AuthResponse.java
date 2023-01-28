package com.backend.api_gateway.entities;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class AuthResponse {

    private String userEmail;
    private String userPassword;
    private Set<Role> roles;
}
