package com.rathercruel.forum.services.impl;

import com.rathercruel.forum.models.Comment;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.repositories.CommentRepository;
import com.rathercruel.forum.services.ArticleService;
import com.rathercruel.forum.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleService articleService;

    @Override
    public Comment saveComment(Long id, String content) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return commentRepository.save(new Comment(articleService.findById(id).get(), content, user.getUsername()));
    }
}
