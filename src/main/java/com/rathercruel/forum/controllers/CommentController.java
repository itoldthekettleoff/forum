package com.rathercruel.forum.controllers;

import com.rathercruel.forum.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/article/comment-{id}")
    public String articleCommentUpdate(@PathVariable Long id, @RequestParam("content") String content) {
        commentService.saveArticleComment(id, content);
        return "redirect:/article/" + id;
    }

    @PostMapping("/user/comment-{id}")
    public String userCommentUpdate(@PathVariable Long id, @RequestParam("content") String content) {
        commentService.saveUserComment(id, content);
        return "redirect:/user/" + id;
    }
}
