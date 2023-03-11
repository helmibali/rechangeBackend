package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.City;
import com.helmi.TunningMarket.entities.Country;
import com.helmi.TunningMarket.entities.Marque;
import com.helmi.TunningMarket.entities.Modele;
import com.helmi.TunningMarket.repositories.MarqueRepository;
import com.helmi.TunningMarket.repositories.ModeleRepository;
import com.helmi.TunningMarket.requests.CityRequest;
import com.helmi.TunningMarket.requests.ModeleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeleService {

    @Autowired
    private ModeleRepository modeleRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    public List<Modele> getModeles(){
        return modeleRepository.findAll();
    };

    public Modele getModeleById(int id){ return modeleRepository.findById(id);};

    public List<Modele> getModelByMarque_id(int id){
        return modeleRepository.findByMarque_Id(id);
    }

    public Modele saveModele(ModeleRequest modeleRequest) {
        Marque marque = marqueRepository.findById(modeleRequest.marque_id);
        Modele modele = new Modele();
        modele.setLibelleModele(modeleRequest.libelleModele);
        modele.setMarque(marque);
    return  modeleRepository.save(modele) ;
    }

    public Modele updateModele(ModeleRequest modeleRequest, int id){
        Marque marque = marqueRepository.findById(modeleRequest.marque_id);
        Modele modele = modeleRepository.findById(id);
        modele.setLibelleModele(modeleRequest.libelleModele);
        modele.setMarque(marque);
        return  modeleRepository.save(modele) ;

    }

    public void DeleteModeleById( int id ){ this.modeleRepository.deleteById(id);  }


}
