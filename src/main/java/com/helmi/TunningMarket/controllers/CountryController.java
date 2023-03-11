package com.helmi.TunningMarket.controllers;
import com.helmi.TunningMarket.entities.Country;
import com.helmi.TunningMarket.requests.CountryRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("pays/liste")
    public List<Country> getAllCountry(){
        return countryService.getAllCountry();
    }
    @GetMapping("pays/{id}")
    public Country getCountryById(@PathVariable int id){
        return countryService.getCountryById(id);
    }
    @PostMapping("pays/add")
    public Country addCategorie(@RequestBody CountryRequest countryRequest){
        return countryService.addCountry(countryRequest);
    }
    @PutMapping("pays/update/{id}")
    public Country updateCountry(@RequestBody CountryRequest countryRequest ,@PathVariable int id){
        return countryService.updateCountry(countryRequest,id);
    }
    @DeleteMapping("pays/delete/{id}")
    public ResponseEntity<?> DeleteCountry(@PathVariable int id){
        try {
            countryService.DeleteCountry(id);
            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Pays supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Pays introuvable!");
        }
    }
}
