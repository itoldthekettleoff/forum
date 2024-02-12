package com.rathercruel.forum.services;

import com.rathercruel.forum.models.Tag;

import java.util.Optional;
import java.util.Set;

public interface TagService {
    Tag add(Tag tag);
    void delete(Tag tag);
    Tag addByString(String tagName);
    Optional<Tag> findByName(String name);
    Set<Tag> formatString(String tags);
}
