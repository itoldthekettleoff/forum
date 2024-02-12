package com.rathercruel.forum.dao.impl;

import com.rathercruel.forum.dao.DAO;
import com.rathercruel.forum.models.ArticleComment;
import com.rathercruel.forum.repositories.ArticleCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ArticleCommentDAOImpl implements DAO<ArticleComment> {

    @Autowired
    private ArticleCommentRepository repository;

    @Override
    public Optional<ArticleComment> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public ArticleComment save(ArticleComment articleComment) {
        return repository.save(articleComment);
    }

    @Override
    public ArticleComment update(ArticleComment articleComment) {
        return repository.save(articleComment);
    }

    @Override
    public void delete(ArticleComment articleComment) {
        repository.delete(articleComment);
    }
}
