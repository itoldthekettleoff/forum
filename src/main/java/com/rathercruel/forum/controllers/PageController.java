package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import com.rathercruel.forum.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String home() { return "redirect:/latest"; }

    @GetMapping("/latest")
    public String latest(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userURL = "/user/" + user.getId();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("user_url", userURL);

        model.addAttribute("current_page", "Latest");
        model.addAttribute("articles", articleService.findAll());
        return "index";
    }
}
