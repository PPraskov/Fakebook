package com.example.demo.repositories;

import com.example.demo.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

    Role findByAuthority(String authority);
}
