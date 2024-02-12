package com.rathercruel.forum.dao.impl;

import com.rathercruel.forum.dao.ArticleDAO;
import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.models.Tag;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ArticleDAOImpl implements ArticleDAO {

    @Autowired
    private ArticleRepository repository;

    @Override
    public Optional<Article> get(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Article> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Article> getAllByTag(Tag tag) {
        return repository.findAllByArticleTags(tag);
    }

    @Override
    public List<Article> getAllByDate(String date) {
        return repository.findAllByDate(date);
    }

    @Override
    public List<Article> getAllByAuthor(User author) {
        return repository.findAllByAuthor(author);
    }

    @Override
    public Article save(Article article) {
        return repository.save(article);
    }

    @Override
    public Article update(Article article) {
        return repository.save(article);
    }

    @Override
    public void delete(Article article) {
        repository.delete(article);
    }
}
