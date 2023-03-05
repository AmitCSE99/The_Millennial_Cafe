package com.backend.restaurant_service.entities;

import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequest {

    private String menuId;
    private List<Customise> customisations;
}
