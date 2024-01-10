package com.rathercruel.forum.services.impl;

import com.rathercruel.forum.models.Role;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.repositories.RoleRepository;
import com.rathercruel.forum.repositories.UserRepository;
import com.rathercruel.forum.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void registerUser(String username, String email, String password) {
        if (!roleRepository.findByAuthority("USER").isPresent()) {
            roleRepository.save(new Role("USER"));
        }

        String encodedPassword = encoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        userRepository.save(new User(username, email, encodedPassword, authorities, new ArrayList<>()));
    }
}
