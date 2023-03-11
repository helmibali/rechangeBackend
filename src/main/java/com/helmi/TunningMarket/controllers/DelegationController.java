package com.helmi.TunningMarket.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmi.TunningMarket.entities.Country;
import com.helmi.TunningMarket.entities.Delegation;
import com.helmi.TunningMarket.entities.Modele;
import com.helmi.TunningMarket.requests.CountryRequest;
import com.helmi.TunningMarket.requests.DelegationRequest;
import com.helmi.TunningMarket.requests.ModeleRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DelegationController {
    @Autowired
    DelegationService delegationService;

    @GetMapping("delegations")
    public List<Delegation> getDelegations(){
        return delegationService.getDelegations();
    }

    @GetMapping("delegation/{id}")
    public Delegation getDelegationById(@PathVariable Long id) { return delegationService.getDelegationById(id);}
    @GetMapping("delegations/{id}")
    public List <Delegation> getDelegationByGouvernorat_id(@PathVariable Long id) { return delegationService.GetDeleGationByGouvernoratId(id);}

    @PostMapping("delegation")
    public Delegation saveDelegation(@RequestBody DelegationRequest delegationRequest){ return delegationService.saveDelegation(delegationRequest);}


    @PostMapping("delegations")
    public Delegation adddelegation(@RequestParam String delegation) throws JsonParseException, JsonMappingException, Exception {
        DelegationRequest d = new ObjectMapper().readValue(delegation, DelegationRequest.class);
        return delegationService.saveDelegation(d);
    }
    @DeleteMapping("delegation/{id}")
    public ResponseEntity<?> DeleteMarque(@PathVariable long id){

        try {

            delegationService.DeleteDelegationById(id);

            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Delegation supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Delegation introuvable!");
        }
    }

}