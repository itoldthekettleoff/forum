package com.rathercruel.forum.services.impl;

import com.rathercruel.forum.dao.ArticleDAO;
import com.rathercruel.forum.dao.DAO;
import com.rathercruel.forum.models.ArticleComment;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.models.UserComment;
import com.rathercruel.forum.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private DAO<ArticleComment> articleCommentDAO;

    @Autowired
    private DAO<UserComment> userCommentDAO;

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Optional<ArticleComment> findArticleCommentById(Long id) {
        return articleCommentDAO.get(id);
    }

    @Override
    public Optional<UserComment> findUserCommentById(Long id) {
        return userCommentDAO.get(id);
    }

    @Override
    public void saveArticleComment(UUID id, String content) {
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleCommentDAO.save(new ArticleComment(articleDAO.get(id).get(), content, author));
    }

    @Override
    public void saveUserComment(User user, String content) {
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userCommentDAO.save(new UserComment(user, content, author));
    }

    @Override
    public void deleteArticleComment(Long id) {
        articleCommentDAO.delete(articleCommentDAO.get(id).get());
    }

    @Override
    public void deleteUserComment(Long id) {
        userCommentDAO.delete(userCommentDAO.get(id).get());
    }

    @Override
    public void updateArticleComment(ArticleComment comment) {
        articleCommentDAO.save(comment);
    }

    @Override
    public void updateUserComment(UserComment comment) {
        userCommentDAO.save(comment);
    }
}
