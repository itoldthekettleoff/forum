package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.UUID;

@Controller
public class ArticlePageController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{uuid}")
    public String articlePage(Model model, @PathVariable("uuid")UUID id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        if (articleService.findById(id).isPresent()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Article article = articleService.findById(id).get();
            String formattedDate = article.getDate().replace(".", "-");
            model.addAttribute("id", id);
            model.addAttribute("article", article);
            model.addAttribute("date_formatted", formattedDate);
            model.addAttribute("images", new ArrayList<String>());
            // article.getImages() -> List<String> with image URLs

            boolean isAdmin = auth != null &&
                    auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
            model.addAttribute("isAdmin", isAdmin);
            return "article-page";
        } else
            return "not-found";
    }
}
