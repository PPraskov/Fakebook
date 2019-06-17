package com.example.demo.services;


import com.example.demo.domain.entities.Role;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.seedRolesInDb();
    }


    private void seedRolesInDb() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setAuthority("ROLE_ADMIN");
            Role user = new Role();
            user.setAuthority("ROLE_USER");
            this.roleRepository.saveAndFlush(admin);
            this.roleRepository.saveAndFlush(user);
        }
    }

    @Override
    public Set<Role> findAllRoles() {
        //noinspection unchecked
        return (Set<Role>) this.roleRepository.findAll();
    }

    @Override
    public Role findByAuthority(String authority) {
        return this.roleRepository.findByAuthority(authority);
    }
}
