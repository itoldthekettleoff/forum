package com.rathercruel.forum.services.impl;

import com.rathercruel.forum.dao.TagDAO;
import com.rathercruel.forum.models.Tag;
import com.rathercruel.forum.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO tagDAO;

    @Override
    public Tag add(Tag tag) {
        return tagDAO.save(tag);
    }

    @Override
    public void delete(Tag tag) {
        tagDAO.delete(tag);
    }

    @Override
    public Tag addByString(String tagName) {
        return tagDAO.save(new Tag(tagName));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return tagDAO.getByString(name);
    }

    @Override
    public Set<Tag> formatString(String tags) {
        Set<Tag> articleTags = new HashSet<>();
        if (tags != null) {
            tags = tags.toLowerCase();
            tags = tags.replace('?', '\0');
            tags = tags.replace('!', '\0');
            tags = tags.replace('&', '\0');
            tags = tags.replace('*', '\0');
            tags = tags.replace('"', '\0');
            tags = tags.replace('/', '\0');
            tags = tags.replace('\'', '\0');
            tags = tags.replace('\\', '\0');
            String[] tagList = tags.split(",");
            for (String tag : tagList) {
                if (tag.isBlank())
                    continue;
                tag = tag.trim();
                tag = tag.replace(' ', '-');
                if (findByName(tag).isPresent()) {
                    articleTags.add(findByName(tag).get());
                } else {
                    articleTags.add(addByString(tag));
                }
            }
        }
        return articleTags;
    }
}
