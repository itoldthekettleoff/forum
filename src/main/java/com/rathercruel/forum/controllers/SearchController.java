package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import com.rathercruel.forum.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class SearchController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/date/{date}")
    public String articlesOfDate(@PathVariable("date") String date, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        String formattedDate = date.replace("-", ".");
        model.addAttribute("current_date", formattedDate);
        model.addAttribute("articles", articleService.findAllByDate(formattedDate));
        return "index";
    }

    @GetMapping("/tag/{tag}")
    public String articlesWithTag(@PathVariable("tag") String tag, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        if (tagService.findByName(tag).isPresent()) {
            model.addAttribute("tag", tag);
            model.addAttribute(
                    "articles", articleService.findAllByTag(tagService.findByName(tag).get())
            );
            return "index";
        } else
            return "not-found";
    }
}
