package com.helmi.TunningMarket.requests;

import com.helmi.TunningMarket.entities.Modele;
import lombok.Data;

import java.util.Set;
@Data
public class MoteurRequest {
    private Long id;
    private String Libelle;
    private String carburant;
    private Set<Modele> modeles;
}
