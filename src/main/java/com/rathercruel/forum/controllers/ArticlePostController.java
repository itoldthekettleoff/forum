package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticlePostController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/post")
    public String postArticlePage() {
        return "article-post";
    }

    @PostMapping("/article/post")
    public String postArticleMethod(@RequestParam String title,
                                    @RequestParam String text) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Article article = articleService.insert(new Article(title, user.getUsername(), text));
        return "redirect:/article/" + article.getId();
    }
}
