package com.rathercruel.forum.models;

import jakarta.persistence.*;

@Entity
@Table(name = "articles")
public class Article {
    private String title;
    private String author;
    private String text;
//    private String lastActivity;
//    private int views;
//    private List<User> userList;

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

    public Article(String title, String author, String text) {
        this.title = title;
        this.author = author;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }
}
