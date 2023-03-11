package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnoreProperties(value="commandes")
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="cart_commande",
            joinColumns = @JoinColumn(name="commande_id"),
            inverseJoinColumns = @JoinColumn(name="cart_id"))
    private List<Cart> carts;

    @JsonIgnoreProperties(value="commandes")
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="cart_commande",
            joinColumns = @JoinColumn(name="commande_id"),
            inverseJoinColumns = @JoinColumn(name="cart_id"))
    private List<Panier> paniers;

    @ManyToOne
    @JoinColumn(name = "username")
    private  User user;
    private int qty;
    private double prixCommande;
    private Date dateCreation;
    private String livraison;
    @Column(name = "validation")
    private boolean validation;
    @Column(name = "annulation")
    private boolean annulation;
    @ManyToOne
    @JoinColumn(name = "delegation_id")
    private Delegation delegation;
    private String address;

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }



    public boolean isAnnulation() {
        return annulation;
    }

    public void setAnnulation(boolean annulation) {
        this.annulation = annulation;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLivraison() {
        return livraison;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }



    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrixCommande() {
        return prixCommande;
    }

    public void setPrixCommande(double prixCommande) {
        this.prixCommande = prixCommande;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commande() {
    }

    public Commande(Long id, List<Cart> carts, User user, int qty, double prixCommande, Date dateCreation, String livraison, boolean validation, boolean annulation, Delegation delegation, String address) {
        this.id = id;
        this.carts = carts;
        this.user = user;
        this.qty = qty;
        this.prixCommande = prixCommande;
        this.dateCreation = dateCreation;
        this.livraison = livraison;
        this.validation = validation;
        this.annulation = annulation;
        this.delegation = delegation;
        this.address = address;
    }

    public List<Panier> getPaniers() {
        return paniers;
    }

    public void setPaniers(List<Panier> paniers) {
        this.paniers = paniers;
    }

    public Commande(Long id, List<Cart> carts, List<Panier> paniers, User user, int qty, double prixCommande, Date dateCreation, String livraison, boolean validation, boolean annulation, Delegation delegation, String address) {
        this.id = id;
        this.carts = carts;
        this.paniers = paniers;
        this.user = user;
        this.qty = qty;
        this.prixCommande = prixCommande;
        this.dateCreation = dateCreation;
        this.livraison = livraison;
        this.validation = validation;
        this.annulation = annulation;
        this.delegation = delegation;
        this.address = address;
    }
}
