package com.rathercruel.forum.services.impl;

import com.rathercruel.forum.dao.UserDAO;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean isPresent(String username) {
        return userDAO.getByUsername(username).isPresent();
    }

    @Override
    public boolean isPresentEmail(String email) {
        return userDAO.getByEmail(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found! :("));
    }

    @Override
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        return userDAO.get(id).orElseThrow(() -> new UsernameNotFoundException("User not found! :("));
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return userDAO.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found! :("));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDAO.getByUsername(username);
    }

    @Override
    public User update(User user) {
        return userDAO.save(user);
    }

    @Override
    public User updateSettings(String username, String nickname, String status, String password) {
        User user = findByUsername(username).get();
        if (!nickname.isBlank())
            user.setNickname(nickname);
        if (!status.isBlank())
            user.setStatus(status);
        if (!password.isBlank())
            user.setPassword(encoder.encode(password));
        return userDAO.save(user);
    }
}
