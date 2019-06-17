package com.example.demo.services;

import com.example.demo.domain.entities.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> findAllRoles();

    Role findByAuthority(String authority);
}
