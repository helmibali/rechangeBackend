package com.helmi.TunningMarket.controllers;

import com.helmi.TunningMarket.entities.Categorie;
import com.helmi.TunningMarket.entities.Delegation;
import com.helmi.TunningMarket.requests.CategorieRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategotrieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping("categories")
    public List<Categorie> getAllCategories(){
        return categorieService.getAllCategories();
    }
    @GetMapping("categorie/{id}")
    public Categorie getCategorieById(@PathVariable int id){
        return categorieService.getCategorieById(id);
    }
    @PostMapping("categorie/add")
    public Categorie addCategorie(@RequestBody CategorieRequest categorieRequest){
        return categorieService.addCategorie(categorieRequest);
    }
    @PutMapping("categorie/update/{id}")
    public Categorie updateCategorie(@RequestBody CategorieRequest categorieRequest ,@PathVariable int id){
        return categorieService.updateCategorie(categorieRequest,id);
    }
    @DeleteMapping("categorie/delete/{id}")
    public ResponseEntity<?> DeleteCategorie(@PathVariable int id){
        try {
            categorieService.DeleteCategorie(id);
            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Categorie supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Categorie introuvable!");
        }
    }

    @GetMapping("categoriesByFamille/{id}")
    public List <Categorie> categoriesByFamille_id(@PathVariable Long id) { return categorieService.findByFamille_id(id);}

}
