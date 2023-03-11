package com.helmi.TunningMarket.entities;

import javax.persistence.*;
import java.util.Date;
@Table(name = "msg")
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id")
    private long id;
    @Column(name="message",length = 2048)
    private String message;
    @OneToOne
    @JoinColumn(name = "username")
    private User auteur;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User emiter;
    private Date dateCreation;
    private boolean vu;

    public boolean isVu() {
        return vu;
    }

    public void setVu(boolean vu) {
        this.vu = vu;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuteur() {
        return auteur;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }

    public User getEmiter() {
        return emiter;
    }

    public void setEmiter(User emiter) {
        this.emiter = emiter;
    }

    public Message(long id, String message, User auteur, User emiter, Date dateCreation, boolean vu) {
        this.id = id;
        this.message = message;
        this.auteur = auteur;
        this.emiter = emiter;
        this.dateCreation = dateCreation;
        this.vu = vu;
    }

    public Message() {
    }
}
