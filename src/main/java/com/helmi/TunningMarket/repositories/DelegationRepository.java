package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Delegation;
import com.helmi.TunningMarket.entities.Modele;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DelegationRepository extends JpaRepository<Delegation, Long> {
    List<Delegation> findByGouvernorat_Id(Long id);
    @Query("SELECT new Delegation (d.id,d.libelle)FROM Delegation d where d.id=:id")
    Delegation findDelegationPublic(Long id);

}
