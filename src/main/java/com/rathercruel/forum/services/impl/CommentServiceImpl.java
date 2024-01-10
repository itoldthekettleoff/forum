package com.rathercruel.forum.services.impl;

import com.rathercruel.forum.models.ArticleComment;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.models.UserComment;
import com.rathercruel.forum.repositories.ArticleCommentRepository;
import com.rathercruel.forum.repositories.UserCommentRepository;
import com.rathercruel.forum.services.ArticleService;
import com.rathercruel.forum.services.CommentService;
import com.rathercruel.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ArticleCommentRepository articleCommentRepository;

    @Autowired
    private UserCommentRepository userCommentRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Override
    public void saveArticleComment(Long id, String content) {
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleCommentRepository.save(new ArticleComment(articleService.findById(id).get(), content, author));
    }

    @Override
    public void saveUserComment(Long id, String content) {
        User user = userService.findByUsername(userService.loadUserById(id).getUsername()).get();
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userCommentRepository.save(new UserComment(user, content, author));
    }
}
