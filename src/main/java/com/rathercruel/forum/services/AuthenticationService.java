package com.rathercruel.forum.services;

public interface AuthenticationService {
    void registerUser(String username, String email, String password);
}
