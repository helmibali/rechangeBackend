package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Modele;
import com.helmi.TunningMarket.entities.Moteur;
import com.helmi.TunningMarket.repositories.MoteurRepository;
import com.helmi.TunningMarket.requests.ModeleRequest;
import com.helmi.TunningMarket.requests.MoteurRequest;
import com.helmi.TunningMarket.services.MoteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MoteurController {
    @Autowired
    MoteurRepository moteurRepository;
    @Autowired
    MoteurService moteurService;

    @GetMapping("moteurs")
    public List<Moteur> listeMoteurs(){
        return moteurRepository.findAll();
    }

    @GetMapping("moteur/{id}")
    public Moteur mouteurById(@PathVariable Long id ){
        return moteurRepository.findById(id).get();
    }

    @GetMapping("moteurByModele/{id}")
    public List<Moteur> moteursByModele(@PathVariable int id){
        return moteurRepository.findAllByModele(id);
    }
    @PostMapping("moteur")
    public Moteur addMoteur(@RequestParam String moteur) throws JsonParseException, JsonMappingException, Exception {
        MoteurRequest m = new ObjectMapper().readValue(moteur, MoteurRequest.class);
        return moteurService.saveMoteur(m);
    }
}
