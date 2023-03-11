package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "marques")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Marque {
    @Id
    @Column(name="marque_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "libelle_marque")
    private String libelleMarque;

    @Column(name="filename")
    private String filename;


    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "marque_id")
    private Set<Modele> modeles;

    @Lob
    @JsonIgnore
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;


}
