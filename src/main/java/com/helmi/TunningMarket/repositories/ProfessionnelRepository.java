package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.ImageData;
import com.helmi.TunningMarket.entities.Professionnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionnelRepository extends JpaRepository<Professionnel, Long> {
    Optional<Professionnel> findByNomAndId(String nom,Long id);
}
