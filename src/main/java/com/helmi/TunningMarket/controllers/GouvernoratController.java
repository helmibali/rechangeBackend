package com.helmi.TunningMarket.controllers;

import com.helmi.TunningMarket.entities.Gouvernorat;
import com.helmi.TunningMarket.requests.GouvernoratRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.GouvernoratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GouvernoratController {

    @Autowired
    GouvernoratService gouvernoratService;
    @GetMapping("gouvernorats")
    public List<Gouvernorat> getGouvernorats(){return gouvernoratService.getGouvernorat();}
    @GetMapping("gouvernorat/{id}")
    public Gouvernorat getGouvernoratById(@PathVariable Long id) {return gouvernoratService.getGouvernoratById(id);}
    @PostMapping("gouvernorat")
    public Gouvernorat saveG(@RequestBody GouvernoratRequest gouvernoratRequest){return gouvernoratService.saveGouvernorat(gouvernoratRequest);}
    @PutMapping("gouvernorat/{id}")
    public Gouvernorat updategouv(@RequestBody GouvernoratRequest gouvernoratRequest, @PathVariable Long id){
        return gouvernoratService.updateGouvernorat(gouvernoratRequest,id);}

    @DeleteMapping("gouvernorat/{id}")
    public ResponseEntity<?> DeleteMarque(@PathVariable long id){

        try {

            gouvernoratService.DeleteGouvernoratById(id);

            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Gouvernorat supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Gouvernorat introuvable!");
        }
    }

}
