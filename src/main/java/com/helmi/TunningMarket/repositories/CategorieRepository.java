package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Integer> {
    Categorie findById(int id);
    List<Categorie> findCategorieByFamille_Id(Long id);
}
