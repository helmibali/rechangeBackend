package com.helmi.TunningMarket.requests;

import com.helmi.TunningMarket.entities.Produit;
import com.helmi.TunningMarket.entities.User;

import javax.persistence.*;
import java.util.Date;

public class CartRequest {
    private long id;
    private Long produit;
    private String user;
    private double prix;
    private Date dateCreation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getProduit() {
        return produit;
    }

    public void setProduit(Long produit) {
        this.produit = produit;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
