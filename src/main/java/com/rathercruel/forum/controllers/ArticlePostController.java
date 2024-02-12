package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import com.rathercruel.forum.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/article")
public class ArticlePostController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;
    
    @GetMapping("/post")
    public String postArticlePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "article-post";
    }

    @PostMapping("/post")
    public String postArticleMethod(@RequestParam String title,
                                    @RequestParam String text,
                                    @RequestParam String tags) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Article article = articleService.addByStrings(tagService.formatString(tags), title, user, text);
        return "redirect:/article/" + article.getId();
    }
}
