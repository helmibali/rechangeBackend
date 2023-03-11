package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "deleg")
public class Delegation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "delegation_id")
    private Long id;
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "gouvernorat_id")
    private Gouvernorat gouvernorat;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "delegation_id")
    private List<Produit> produits;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "delegation_id")
    private List<User> users;

    public Delegation(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
}
