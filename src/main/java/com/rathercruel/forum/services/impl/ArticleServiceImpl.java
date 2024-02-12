package com.rathercruel.forum.services.impl;

import com.rathercruel.forum.dao.ArticleDAO;
import com.rathercruel.forum.models.Article;
import com.rathercruel.forum.models.Tag;
import com.rathercruel.forum.models.User;
import com.rathercruel.forum.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Article add(Article article) {
        return articleDAO.save(article);
    }

    @Override
    public Article addByStrings(Set<Tag> articleTags, String title, User user, String text) {
        return articleDAO.save(new Article(articleTags, title, user, text));
    }

    @Override
    public void delete(Article article) {
        articleDAO.delete(article);
    }

    @Override
    public List<Article> findAll() {
        return articleDAO.getAll();
    }

    @Override
    public Optional<Article> findById(UUID id) {
        return articleDAO.get(id);
    }

    @Override
    public List<Article> findAllByDate(String date) {
        return articleDAO.getAllByDate(date);
    }

    @Override
    public List<Article> findAllByAuthor(User author) {
        return articleDAO.getAllByAuthor(author);
    }

    @Override
    public List<Article> findAllByTag(Tag tag) {
        return articleDAO.getAllByTag(tag);
    }
}
