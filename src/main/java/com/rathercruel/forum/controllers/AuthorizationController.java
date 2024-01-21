package com.rathercruel.forum.controllers;

import com.rathercruel.forum.services.AuthenticationService;
import com.rathercruel.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam(name = "username") String username,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password) {
        if (userService.isPresent(username.toLowerCase()) || userService.isPresentEmail(email.toLowerCase()))
            return "redirect:/registration?error";
        else {
            authenticationService.registerUser(username.toLowerCase(), email.toLowerCase(), password);
            return "redirect:/login";
        }
    }
}
