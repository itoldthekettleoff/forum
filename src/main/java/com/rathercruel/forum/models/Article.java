package com.rathercruel.forum.models;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private int views;
    private String date;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<ArticleComment> articleComments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "article_tags",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> articleTags;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(
            name = "article_sequence",
            sequenceName = "article_sequence")
    private Long id;

    public Article() {
    }

    public Article(Set<Tag> articleTags, String title, User author, String content) {
        this.articleTags = articleTags;
        this.title = title;
        this.author = author;
        this.content = content;

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        date = sdf.format(new Date());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<ArticleComment> getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(List<ArticleComment> articleComments) {
        this.articleComments = articleComments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Tag> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(Set<Tag> articleTags) {
        this.articleTags = articleTags;
    }
}
