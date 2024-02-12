package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String home() { return "redirect:/latest"; }

    @GetMapping("/latest")
    public String latest(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("current_page", "Latest");
        model.addAttribute("articles", articleService.findAll());
        return "index";
    }
}
