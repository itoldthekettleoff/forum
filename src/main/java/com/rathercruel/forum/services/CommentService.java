package com.rathercruel.forum.services;

import com.rathercruel.forum.models.Comment;

public interface CommentService {
    Comment saveComment(Long id, String content);
}
