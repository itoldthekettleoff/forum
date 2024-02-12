package com.rathercruel.forum.dao.impl;

import com.rathercruel.forum.dao.DAO;
import com.rathercruel.forum.models.UserComment;
import com.rathercruel.forum.repositories.UserCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserCommentDAOImpl implements DAO<UserComment> {

    @Autowired
    private UserCommentRepository repository;

    @Override
    public Optional<UserComment> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public UserComment save(UserComment userComment) {
        return repository.save(userComment);
    }

    @Override
    public UserComment update(UserComment userComment) {
        return repository.save(userComment);
    }

    @Override
    public void delete(UserComment userComment) {
        repository.delete(userComment);
    }
}
