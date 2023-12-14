package com.rathercruel.forum.models;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {
    private String title;
    private String author;

    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private int views;

    private String date;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "tags_fk", referencedColumnName = "id")
//    private List<Tag> tags;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(
            name = "article_sequence",
            sequenceName = "article_sequence"
    )
    private Long id;

    public Article() {
    }

    public Article(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        date = sdf.format(new Date());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }
}
