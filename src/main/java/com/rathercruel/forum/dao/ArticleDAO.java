package com.rathercruel.forum.dao;

import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.models.Tag;
import com.rathercruel.forum.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleDAO {
    Optional<Article> get(UUID id);
    List<Article> getAll();
    List<Article> getAllByTag(Tag tag);
    List<Article> getAllByDate(String date);
    List<Article> getAllByAuthor(User author);
    Article save(Article article);
    Article update(Article article);
    void delete(Article article);
}
