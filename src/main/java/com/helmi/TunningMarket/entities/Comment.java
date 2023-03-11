package com.helmi.TunningMarket.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2048)
    private String text;
    @Column(length = 2048)
    private String texte;
    private Date dateComment;
    private Long parentId;

    @ManyToOne
    @JsonIgnoreProperties(value = {"password","roles"})
    @JoinColumn(name = "username")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Comment(Long id, String text, Date dateComment, Long parentId, User user, Article article) {
        this.id = id;
        this.text = text;
        this.dateComment = dateComment;
        this.parentId = parentId;
        this.user = user;
        this.article = article;
    }

    public Comment() {
    }
}
