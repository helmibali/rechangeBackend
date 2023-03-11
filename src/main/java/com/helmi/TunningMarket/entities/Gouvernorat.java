package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gouv")
public class Gouvernorat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gouvernorat_id")
    private Long id;
    @Column(name = "libelle")
    private String libelle;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "gouvernorat_id")
    private List<Delegation> delegations;

}
