package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import com.rathercruel.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/user/{id}")
    public String userProfile(@PathVariable("id") Long id, Model model) {
        String username = userService.loadUserById(id).getUsername();
        User user = userService.findByUsername(username).get();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("user_status", user.getStatus());
        model.addAttribute("user_regdate", user.getDate());
        model.addAttribute("articles", articleService.findAllByAuthor(user));
        model.addAttribute("comments", user.getUserComments());
        return "profile";
    }
}
