package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Famille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="famille_id")
    private Long id;
    private String libelle;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "famille_id")
    private List<Categorie> categories;
}
