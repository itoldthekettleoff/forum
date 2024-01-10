package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.models.Tag;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import com.rathercruel.forum.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

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
        String userURL = "/user/" + user.getId();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("user_url", userURL);
        return "article-post";
    }

    @PostMapping("/post")
    public String postArticleMethod(@RequestParam String title,
                                    @RequestParam String text,
                                    @RequestParam String tags) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Tag> articleTags = new HashSet<>();
        if (tags != null) {
            String[] tagList = tags.split(",");
            for (String tag : tagList) {
                if (tag.isBlank())
                    continue;
                if (tagService.findByName(tag.trim()).isPresent()) {
                    articleTags.add(tagService.findByName(tag.trim()).get());
                } else {
                    tagService.insert(new Tag(tag.trim()));
                    articleTags.add(new Tag(tag.trim()));
                }
            }
        }
        Article article = articleService.insert(new Article(articleTags, title, user, text));
        return "redirect:/article/" + article.getId();
    }
}
