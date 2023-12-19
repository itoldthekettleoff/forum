package com.rathercruel.forum.repositories;

import com.rathercruel.forum.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByDate(String date);
    List<Article> findAllByAuthor(String author);
}
