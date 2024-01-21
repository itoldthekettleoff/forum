package com.rathercruel.forum.controllers;

import com.rathercruel.forum.models.ArticleComment;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.models.UserComment;
import com.rathercruel.forum.services.CommentService;
import com.rathercruel.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/article/{uuid}")
    public String articleCommentUpdate(@PathVariable("uuid") UUID id,
                                       @RequestParam("content") String content) {
        commentService.saveArticleComment(id, content);
        return "redirect:/article/" + id;
    }

    @PostMapping("/user/{username}")
    public String userCommentUpdate(@PathVariable("username") String username,
                                    @RequestParam("content") String content) {
        if (userService.findByUsername(username).isPresent()) {
            User user = userService.findByUsername(username).get();
            commentService.saveUserComment(user, content);
            return "redirect:/user/" + username;
        } else
            return "not-found";
    }

    @PostMapping("/delete/article/{article_id}/{comment_id}")
    private String deleteArticleComment(@PathVariable("article_id") UUID articleId,
                                        @PathVariable("comment_id") Long id) {
        commentService.deleteArticleComment(id);
        return "redirect:/article/" + articleId;
    }

    @PostMapping("/edit/article/{article_id}/{comment_id}")
    private String updateArticleComment(@PathVariable("article_id") UUID articleId,
                                        @PathVariable("comment_id") Long id,
                                        @RequestParam("changed_content") String content) {
        ArticleComment comment = commentService.findArticleCommentById(id).get();
        comment.setContent(content);
        commentService.updateArticleComment(comment);
        return "redirect:/article/" + articleId;
    }

    @PostMapping("/delete/user/{username}/{comment_id}")
    private String deleteUserComment(@PathVariable("username") String username,
                                     @PathVariable("comment_id") Long id) {
        commentService.deleteUserComment(id);
        return "redirect:/user/" + username;
    }

    @PostMapping("/edit/user/{username}/{comment_id}")
    private String updateUserComment(@PathVariable("username") String username,
                                     @PathVariable("comment_id") Long id,
                                     @RequestParam("changed_content") String content) {
        UserComment comment = commentService.findUserCommentById(id).get();
        comment.setContent(content);
        commentService.updateUserComment(comment);
        return "redirect:/user/" + username;
    }
}
