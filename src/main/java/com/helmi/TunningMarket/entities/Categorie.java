package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categorie_id")
    private int id;

    @Column(name="nom_categorie")
    private String nomCategorie;

    @Column(name="description_categorie")
    private String descriptionCategorie;


    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "categorie_id")
    private List<Produit> produits;

    @ManyToOne
    @JoinColumn(name = "famille_id")
    private Famille famille;


}
