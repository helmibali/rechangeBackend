package com.helmi.TunningMarket.requests;

import com.helmi.TunningMarket.entities.Modele;
import com.helmi.TunningMarket.entities.Moteur;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.Set;
@Data
public class ProduitRequest {
    public int id;
    public String nomProduit;
    public  Double prixProduit;
    public Date dateCreation;
    public int categorie_id;
    public String filename;
    public Set<Modele> modeles;
    private int annee;
    private String description;
    private String carburant;
    private String user;
    private Long delegation_id;
    private Boolean enabled;
}
