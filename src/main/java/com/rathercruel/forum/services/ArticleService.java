package com.rathercruel.forum.services;

import com.rathercruel.forum.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article insert(Article article);
    List<Article> findAll();
    Optional<Article> findById(Long id);
}
