package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="produit_id")
    private long idProduit;

    @Column(name = "nom_produit")
    private String nomProduit;

    @Column(name="prix_produit")
    private Double prixProduit;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name="filename")
    private String filename;

    @Column(length = 2048)
    private String description;

    @Column(name="carburant")
    private String carburant;

    @Column(name="annee")
    private int annee;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "produit_id")
    private  List<Cart> carts;


    @ManyToOne
    @JoinColumn(name = "delegation_id")
    private Delegation delegation;


    @ManyToOne
    @JsonIgnoreProperties(value = {"password","roles"})
    @JoinColumn(name = "username")
    private User user;


    //@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="produit_modele",
            joinColumns = @JoinColumn(name="produit_id"),
            inverseJoinColumns = @JoinColumn(name="modele_id"))
    private Set<Modele> modeles = new HashSet<>();

    @Lob
    @JsonIgnore
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;


}
