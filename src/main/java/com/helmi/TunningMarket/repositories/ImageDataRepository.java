package com.helmi.TunningMarket.repositories;

import com.helmi.TunningMarket.entities.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String fileName);
}
