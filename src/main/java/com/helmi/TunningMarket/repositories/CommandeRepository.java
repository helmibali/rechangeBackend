package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Command, Long> {
    @Query("select c from Command c order by c.dateCreation desc")
    public List<Command> findAllByDateCreation();
}
