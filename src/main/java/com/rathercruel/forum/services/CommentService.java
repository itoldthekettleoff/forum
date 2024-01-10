package com.rathercruel.forum.services;

import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void saveArticleComment(Long id, String content);
    void saveUserComment(Long id, String content);
}
