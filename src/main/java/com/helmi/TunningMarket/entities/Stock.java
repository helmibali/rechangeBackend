package com.helmi.TunningMarket.entities;

import javax.persistence.*;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer price;
    @Column(name="company_name")
    private String companyName;

}
