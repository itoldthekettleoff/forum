package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import com.rathercruel.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/{username}")
    public String userProfile(@PathVariable("username") String username, Model model) {
        if (userService.findByUsername(username).isPresent()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            User user = userService.findByUsername(username).get();
            model.addAttribute("is_current", username.equals(auth.getName()));
            model.addAttribute("user", user);
            model.addAttribute("articles", articleService.findAllByAuthor(user));

            boolean isAdmin = auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
            model.addAttribute("isAdmin", isAdmin);

            return "profile";
        } else
            return "not-found";
    }

    @GetMapping("/{username}/settings")
    private String userSettings(@PathVariable("username") String username, Model model, Principal auth) {
        if (userService.findByUsername(username).isPresent()) {
            if (auth.getName().equals(username)) {
                User user = userService.findByUsername(username).get();
                model.addAttribute("user", user);
                return "profile-settings";
            } else
                return "redirect:/user/" + auth.getName() + "/settings";
        } else
            return "not-found";
    }

    @PostMapping("/{username}/settings")
    private String updateUser(@PathVariable("username") String username,
                              @RequestParam("nickname") String nickname,
                              @RequestParam("status") String status,
                              @RequestParam("password") String password) {
        if (nickname.isBlank() && status.isBlank() && password.isBlank()) {
            return "redirect:/user/" + username + "/settings";
        } else {
            User user = userService.findByUsername(username).get();
            if (!nickname.isBlank())
                user.setNickname(nickname);
            if (!status.isBlank())
                user.setStatus(status);
            if (!password.isBlank())
                user.setPassword(encoder.encode(password));
            User newUser = userService.update(user);
            return "redirect:/user/" + newUser.getUsername();
        }
    }
}
