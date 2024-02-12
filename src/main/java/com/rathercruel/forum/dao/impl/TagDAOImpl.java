package com.rathercruel.forum.dao.impl;

import com.rathercruel.forum.dao.TagDAO;
import com.rathercruel.forum.models.Tag;
import com.rathercruel.forum.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TagDAOImpl implements TagDAO {

    @Autowired
    private TagRepository repository;

    @Override
    public Optional<Tag> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Tag> getByString(String name) {
        return repository.findByName(name);
    }

    @Override
    public Tag save(Tag tag) {
        return repository.save(tag);
    }

    @Override
    public void delete(Tag tag) {
        repository.delete(tag);
    }
}
