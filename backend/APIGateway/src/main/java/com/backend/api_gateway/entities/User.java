package com.backend.api_gateway.entities;

import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class User {

    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userPassword;
    private List<Address> address;
    private Set<Role> roles;
}
