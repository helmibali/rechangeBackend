package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.Famille;
import com.helmi.TunningMarket.entities.Marque;
import com.helmi.TunningMarket.entities.Modele;
import com.helmi.TunningMarket.repositories.FamilleRepository;
import com.helmi.TunningMarket.requests.FamilleRequest;
import com.helmi.TunningMarket.requests.ModeleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilleService {
    @Autowired
    FamilleRepository familleRepository;
    public List<Famille> familleList(){return familleRepository.findAll();}
    public Famille familleById(Long id){ return familleRepository.findById(id).get();}
    public Famille saveFamille(FamilleRequest familleRequest) {
        Famille famille = new Famille();
        famille.setLibelle(familleRequest.getLibelle());
        return familleRepository.save(famille);
    }
}
