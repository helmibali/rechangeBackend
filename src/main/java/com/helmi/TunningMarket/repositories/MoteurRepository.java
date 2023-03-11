package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Moteur;
import com.helmi.TunningMarket.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoteurRepository extends JpaRepository<Moteur, Long> {
    @Query("select  p from Moteur p join p.modeles m where m.id = :id_mod")
    List<Moteur> findAllByModele(@Param("id_mod") int id_mod);
}
