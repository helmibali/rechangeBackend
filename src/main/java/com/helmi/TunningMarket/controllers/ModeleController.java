package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Modele;
import com.helmi.TunningMarket.requests.ModeleRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.ModeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api")
public class ModeleController {
    @Autowired
    private ModeleService modeleService;

    @GetMapping("/modeleByMarqueId/{id}")
    public List<Modele> getModelesByMarque_id(@PathVariable int id){ return modeleService.getModelByMarque_id(id);}

    @GetMapping("/modeles")
    public List<Modele> getModeles(){ return modeleService.getModeles();}

    @GetMapping("/modele/{id}")
    public Modele getModeleById(@PathVariable int id){
        return modeleService.getModeleById(id);
    }

    @PutMapping("/modele/update/{id}")
    public Modele updateModele(@RequestBody ModeleRequest modeleRequest,@PathVariable int id){
        return modeleService.updateModele(modeleRequest, id);
    }

    @DeleteMapping("/modele/delete/{id}")
        public ResponseEntity<?> DeleteProduit(@PathVariable int id){

            try {
                modeleService.DeleteModeleById(id);
                ApiResponse res = new ApiResponse();
                res.setSuccess(true);
                res.setMessage("Modele supprimé avec succé!");
                return ResponseEntity.ok(res);
            }catch(Exception e) {
                return ResponseEntity.notFound().build().ok("Modele introuvable!");
            }
        }
        @PostMapping("/modele/add")
    public Modele addModele(@RequestParam String modele) throws JsonParseException, JsonMappingException, Exception {
        ModeleRequest m = new ObjectMapper().readValue(modele, ModeleRequest.class);
        return modeleService.saveModele(m);
        }
}
