package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.Modele;
import com.helmi.TunningMarket.entities.Moteur;
import com.helmi.TunningMarket.repositories.ModeleRepository;
import com.helmi.TunningMarket.repositories.MoteurRepository;
import com.helmi.TunningMarket.requests.MoteurRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MoteurService {
    @Autowired
    MoteurRepository moteurRepository;
    @Autowired
    ModeleRepository modeleRepository;

    public Moteur saveMoteur(MoteurRequest moteurRequest){
        Moteur moteur=new Moteur();
        moteur.setLibelle(moteurRequest.getLibelle());
        moteur.setCarburant(moteurRequest.getCarburant());
        moteur.setModeles(moteurRequest.getModeles()
                .stream()
                .map(modeles ->{
                            Modele mods = modeles;
                            if(mods.getId()>0){
                                mods = modeleRepository.findById(mods.getId());
                            }
                            mods.getMouteurs().add(moteur);
                            return mods;
                        }
                ).collect(Collectors.toSet()));
        return moteurRepository.save(moteur);

    }
}
