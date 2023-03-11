package com.helmi.TunningMarket.requests;

public class CategorieRequest {
    public int id;
    public String nomCategorie;
    public String descriptionCategorie;
    private Long famille;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getDescriptionCategorie() {
        return descriptionCategorie;
    }

    public void setDescriptionCategorie(String descriptionCategorie) {
        this.descriptionCategorie = descriptionCategorie;
    }

    public Long getFamille() {
        return famille;
    }

    public void setFamille(Long famille) {
        this.famille = famille;
    }
}
