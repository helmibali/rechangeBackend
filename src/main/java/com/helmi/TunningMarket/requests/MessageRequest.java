package com.helmi.TunningMarket.requests;

import com.helmi.TunningMarket.entities.User;

import java.util.Date;


public class MessageRequest {
    private long id;
    private String message;
    private String auteur;
    private long emiter;
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

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }


    public long getEmiter() {
        return emiter;
    }

    public void setEmiter(long emiter) {
        this.emiter = emiter;
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
}
