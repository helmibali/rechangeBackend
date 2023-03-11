package com.helmi.TunningMarket.requests;

import lombok.Data;

import java.util.Date;
@Data
public class PanierRequest {
    private Long id ;
    private Long produit;
    private String user;
    private Date dateCreation;
    private int prix;
}
