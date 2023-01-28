package com.backend.user_service.services.impl;

import com.backend.user_service.entities.Role;
import com.backend.user_service.repositories.RoleRepository;
import com.backend.user_service.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
