package com.backend.restaurant_service.entities;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequest {

    private String menuId;
    private Set<Customise> customisations;
}
