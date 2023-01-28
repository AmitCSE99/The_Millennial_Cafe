package com.backend.api_gateway.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Role {
    private String roleName;
    private String roleDescription;
}
