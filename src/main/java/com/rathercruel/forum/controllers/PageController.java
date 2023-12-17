package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.repositories.RoleRepository;
import com.rathercruel.forum.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class PageController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String home() { return "redirect:/latest"; }

    @GetMapping("/latest")
    public String latest(Model model) {
        model.addAttribute("current_page", "Latest");
        model.addAttribute("articles", articleService.findAll());
        return "index";
    }

    @GetMapping("/articles/{date}")
    public String articlesOfDate(@PathVariable("date") String date, Model model) {
        model.addAttribute("current_date", date.replace("_", "."));
        model.addAttribute("articles", new ArrayList<Article>()); // List<Article> loaded by date
        return "index";
    }
}
