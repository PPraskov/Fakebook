package com.example.demo.services;


import com.example.demo.domain.entities.Role;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.models.service.UserProfileServiceModel;
import com.example.demo.domain.models.service.UserRegisterServiceModel;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }


    @Override
    public void registerUser(UserRegisterServiceModel model) {
        model.setPassword(this.bCryptPasswordEncoder.encode(model.getPassword()));
        User user = this.modelMapper.map(model, User.class);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        if (this.userRepository.count() == 0) {
            Role adminRole = this.roleService.findByAuthority("ROLE_ADMIN");
            Role role = this.roleService.findByAuthority("ROLE_USER");
            user.setAuthorities(new HashSet<>() {{
                add(adminRole);
                add(role);
            }});
        } else {
            Role role = this.roleService.findByAuthority("ROLE_USER");
            user.setAuthorities(new HashSet<>() {{
                add(role);
            }});
        }
        try {
            this.userRepository.saveAndFlush(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    public UserProfileServiceModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow( () -> new UserNotFoundException());
        return this.modelMapper.map(user, UserProfileServiceModel.class);


    }

    @Override
    public List<UserProfileServiceModel> findAll() {
        return this.userRepository
                .findAll()
                .stream()
                .map(e -> this.modelMapper
                        .map(e, UserProfileServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
    }
}
