package com.rathercruel.forum.dao;

import com.rathercruel.forum.models.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> get(Long id);
    Optional<User> getByUsername(String username);
    Optional<User> getByEmail(String email);
    User save(User user);
    User update(User user);
    void delete(User user);
}
