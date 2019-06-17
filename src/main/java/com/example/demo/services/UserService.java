package com.example.demo.services;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.models.service.UserProfileServiceModel;
import com.example.demo.domain.models.service.UserRegisterServiceModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    void registerUser(UserRegisterServiceModel model);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserProfileServiceModel findByUsername(String username);

    User getUser(String username);

    List<UserProfileServiceModel> findAll();
}
