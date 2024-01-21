package com.rathercruel.forum.services;

import com.rathercruel.forum.models.ArticleComment;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.models.UserComment;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface CommentService {
    Optional<ArticleComment> findArticleCommentById(Long id);
    Optional<UserComment> findUserCommentById(Long id);
    void saveArticleComment(UUID id, String content);
    void saveUserComment(User user, String content);
    void deleteArticleComment(Long id);
    void updateArticleComment(ArticleComment comment);
    void deleteUserComment(Long id);
    void updateUserComment(UserComment comment);
}
