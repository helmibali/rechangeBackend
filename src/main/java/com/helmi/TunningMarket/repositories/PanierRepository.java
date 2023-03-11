package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Cart;
import com.helmi.TunningMarket.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository  extends JpaRepository<Panier, Long> {

    @Query("Select panier  FROM Panier panier WHERE panier.user.username=:username and panier.commandes.size=0")
    List<Panier> getPanierByusername(@Param("username")String username);

    @Query("Select panier  FROM Panier panier WHERE panier.user.username=:username and panier.commandes.size <> 0")
    List<Panier> getPanierByusernameEnCours(@Param("username")String username);

    @Query("Select panier  FROM Panier panier WHERE panier.produit.user.username=:username and panier.commandes.size <> 0")
    List<Panier> getPanierByusernameInCmd(@Param("username")String username);
}
