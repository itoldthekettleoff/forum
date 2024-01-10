package com.rathercruel.forum.services.impl;

import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.models.Tag;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.repositories.ArticleRepository;
import com.rathercruel.forum.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article insert(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findAllByDate(String date) {
        return articleRepository.findByDate(date);
    }

    @Override
    public List<Article> findAllByAuthor(User author) {
        return articleRepository.findAllByAuthor(author);
    }

    @Override
    public List<Article> findAllByTag(Tag tag) {
//        return articleRepository.findAllByTag(tag);
        return null;
    }
}
