package com.rathercruel.forum.dao;

import com.rathercruel.forum.models.Tag;

import java.util.Optional;

public interface TagDAO {
    Optional<Tag> get(Long id);
    Optional<Tag> getByString(String name);
    Tag save(Tag tag);
    void delete(Tag tag);
}
