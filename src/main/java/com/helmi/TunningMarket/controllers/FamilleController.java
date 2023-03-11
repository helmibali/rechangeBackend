package com.helmi.TunningMarket.controllers;

import com.helmi.TunningMarket.entities.Famille;
import com.helmi.TunningMarket.entities.Gouvernorat;
import com.helmi.TunningMarket.repositories.FamilleRepository;
import com.helmi.TunningMarket.requests.FamilleRequest;
import com.helmi.TunningMarket.requests.GouvernoratRequest;
import com.helmi.TunningMarket.services.FamilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FamilleController {
    @Autowired
    FamilleService familleService;
   @GetMapping("/familles")
    public List<Famille> getListeFamilles(){
        return familleService.familleList();
    }
    @GetMapping("/famille/{id}")
    public Famille getFamilleById(@PathVariable Long id){
       return familleService.familleById(id);
    }

    @PostMapping("/famille")
    public Famille saveFamille(@RequestBody FamilleRequest familleRequest){return familleService.saveFamille(familleRequest);}

}
