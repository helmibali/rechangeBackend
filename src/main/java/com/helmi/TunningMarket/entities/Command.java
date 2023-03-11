package com.helmi.TunningMarket.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnoreProperties(value="commandes")
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="cart_command",
            joinColumns = @JoinColumn(name="command_id"),
            inverseJoinColumns = @JoinColumn(name="cart_id"))
    private List<Cart> carts;



    @ManyToOne
    @JoinColumn(name = "username")
    private  User user;
    private int qty;
    private double prixCommande;
    private Date dateCreation;
    private String livraison;
    @Column(name = "validation")
    private boolean validation;
    @Column(name = "annulation")
    private boolean annulation;
    @ManyToOne
    @JoinColumn(name = "delegation_id")
    private Delegation delegation;
    private String address;
}
