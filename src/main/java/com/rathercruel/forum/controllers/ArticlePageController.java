package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class ArticlePageController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{id}")
    public String articlePage(Model model, @PathVariable("id") Long id) {
        if (articleService.findById(id).isPresent()) {
            Article article = articleService.findById(id).get();
            model.addAttribute("id", id);
            model.addAttribute("date", article.getDate());
            model.addAttribute("title", article.getTitle());
            model.addAttribute("author", article.getAuthor());
            model.addAttribute("text", article.getContent());

            model.addAttribute("images", new ArrayList<String>()); // article.getImages() -> List<String> with image URLs
            model.addAttribute("comments", article.getComments());
            return "article-page";
        } else
            return "not-found";
    }
}
