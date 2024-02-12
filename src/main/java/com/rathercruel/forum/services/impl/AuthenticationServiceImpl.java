package com.rathercruel.forum.services.impl;

import com.rathercruel.forum.dao.RoleDAO;
import com.rathercruel.forum.dao.UserDAO;
import com.rathercruel.forum.models.Role;
import com.rathercruel.forum.models.User;
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
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void registerUser(String username, String email, String password) {
        if (!roleDAO.getByAuthority("USER").isPresent()) {
            roleDAO.save(new Role("USER"));
        }

        String encodedPassword = encoder.encode(password);
        Role userRole = roleDAO.getByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        userDAO.save(new User(username, email, encodedPassword, authorities, new ArrayList<>()));
    }
}
