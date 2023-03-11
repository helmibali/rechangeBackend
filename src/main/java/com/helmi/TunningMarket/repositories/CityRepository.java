package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    City findByCityname(String cityname);
    City findById(int id);
}
