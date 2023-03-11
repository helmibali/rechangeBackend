package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Cart;
import com.helmi.TunningMarket.entities.Panier;
import com.helmi.TunningMarket.entities.User;
import com.helmi.TunningMarket.requests.PanierRequest;
import com.helmi.TunningMarket.requests.UserRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PanierController {

    @Autowired
    PanierService panierService;
    @PostMapping("/panier")
    public Panier createArticle (
            @RequestParam("panier") String panier) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Save panier...");
        PanierRequest p = new ObjectMapper().readValue(panier, PanierRequest.class);

        return panierService.save(p);
    }

    @GetMapping("/liste-panier")
    List<Panier> findAll(){return panierService.list();}
    @GetMapping("/liste-panier/{username}")
    List<Panier> findByUsername(@PathVariable String username){return panierService.getPanierByusername(username);}

    @GetMapping("/liste-panier-en-cours/{username}")
    List<Panier> findByUsernameEnCours(@PathVariable String username){return panierService.getPanierByusernameEnCours(username);}

    @GetMapping("/liste-panier-en-commande/{username}")
    List<Panier> findByUsernameEnCmd(@PathVariable String username){return panierService.getPanierByusernameInCmd(username);}
    @DeleteMapping("/panier/{id}")
    public ResponseEntity<?> DeleteProduit(@PathVariable long id){

        try {

            panierService.DeleteCartById(id);

            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Produit supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Produit introuvable!");
        }
    }

}
