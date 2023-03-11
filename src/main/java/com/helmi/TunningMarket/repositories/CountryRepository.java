package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
    Country findById(int id);
}
