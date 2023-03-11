package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="modeles")
public class Modele {
    @Id
    @Column(name="modele_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "libelle_modelle")
    private String libelleModele;

    @ManyToOne
    @JoinColumn(name = "marque_id" )
    private Marque marque;

    @ManyToMany(mappedBy="modeles")
    @JsonIgnore
    private Set<Produit> produits = new HashSet<>();

    @ManyToMany(mappedBy="modeles")
    @JsonIgnore
    private Set<Moteur> mouteurs = new HashSet<>();

    public Modele() {
    }

    public Modele(int id, String libelleModele, Marque marque, Set<Produit> produits, Set<Moteur> mouteurs) {
        this.id = id;
        this.libelleModele = libelleModele;
        this.marque = marque;
        this.produits = produits;
        this.mouteurs = mouteurs;
    }

    public Set<Moteur> getMouteurs() {
        return mouteurs;
    }

    public void setMouteurs(Set<Moteur> mouteurs) {
        this.mouteurs = mouteurs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelleModele() {
        return libelleModele;
    }

    public void setLibelleModele(String libelleModele) {
        this.libelleModele = libelleModele;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
}
