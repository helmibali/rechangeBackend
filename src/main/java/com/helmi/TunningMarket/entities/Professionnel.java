package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "prof")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Professionnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    @Lob
    @JsonIgnore
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;
}
