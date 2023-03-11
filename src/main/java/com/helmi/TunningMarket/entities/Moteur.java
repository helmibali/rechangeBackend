package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Moteur {
    @Id
    @Column(name="moteur_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Libelle;
    private String carburant;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="mouteur_modele",
            joinColumns = @JoinColumn(name="mouteur_id"),
            inverseJoinColumns = @JoinColumn(name="modele_id"))
    private Set<Modele> modeles = new HashSet<>();
/*
    @ManyToMany(mappedBy="moteurs")
    @JsonIgnore
    private Set<Produit> produits = new HashSet<>();
    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }


*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public Set<Modele> getModeles() {
        return modeles;
    }

    public void setModeles(Set<Modele> modeles) {
        this.modeles = modeles;
    }

    public Moteur() {
    }




}
