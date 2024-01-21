package com.rathercruel.forum.controllers;

import com.rathercruel.forum.services.ArticleService;
import com.rathercruel.forum.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/articles/tag")
public class TagsController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/{tag}")
    public String articlesWithTag(@PathVariable("tag") String tag, Model model) {
        System.out.println(tag);
        if (tagService.findByName(tag).isPresent()) {
            model.addAttribute("articles", tagService.findByName(tag).get().getArticles());
        } else {
            model.addAttribute("articles", new ArrayList<>());
        }
        return "index";
    }
}
