package com.helmi.TunningMarket.requests;

public class ContactRequest {
    public Long id;
    public String nom;
    public String prenom;
    public String email;
    public  String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactRequest(Long id, String nom, String prenom, String mail, String message) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = mail;
        this.message = message;
    }

    public ContactRequest() {
    }
}
