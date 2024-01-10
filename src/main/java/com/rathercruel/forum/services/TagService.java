package com.rathercruel.forum.services;

import com.rathercruel.forum.models.Tag;

import java.util.Optional;

public interface TagService {
    Tag insert(Tag tag);
    Optional<Tag> findByName(String name);
}
