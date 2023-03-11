package com.helmi.TunningMarket.requests;
import com.helmi.TunningMarket.entities.Cart;
import com.helmi.TunningMarket.entities.Panier;

import java.util.Date;
import java.util.List;

public class CommandeRequest {
    private long id;
    private List<Cart> carts;
    private List<Panier> paniers;
    private String user;
    private int qty;
    private double prixCommande;
    private Date dateCreation;
    private String livraison;
    private long delegation;
    private String address;
    private boolean validation;
    private boolean annulation;

    public List<Panier> getPaniers() {
        return paniers;
    }

    public void setPaniers(List<Panier> paniers) {
        this.paniers = paniers;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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

    public String getLivraison() {
        return livraison;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }

    public long getDelegation() {
        return delegation;
    }

    public void setDelegation(long delegation) {
        this.delegation = delegation;
    }


}
