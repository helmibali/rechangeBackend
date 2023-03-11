package com.helmi.TunningMarket.repositories;


import com.helmi.TunningMarket.entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarqueRepository extends JpaRepository<Marque,Integer> {
    Marque findById(int id);
    //Optional<Marque> findByIdImg(int id);
}
