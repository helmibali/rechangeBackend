package com.helmi.TunningMarket.controllers;

import com.helmi.TunningMarket.entities.City;
import com.helmi.TunningMarket.requests.CityRequest;
import com.helmi.TunningMarket.response.ApiResponse;
import com.helmi.TunningMarket.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("ville/liste")
    public List<City> getCities() {return cityService.getCities();}

    @GetMapping("ville")
    public City getCity(String cityName){
        return cityService.getCity(cityName);
    }
    @GetMapping ("/ville/{id}")
    public City getCityeById( @PathVariable int id ){
        return this.cityService.getCityById(id);
    }

    @PostMapping("ville/add")
    public City saveCity(@RequestBody CityRequest cityRequest){
        return cityService.saveCity(cityRequest);
    }

    @PutMapping("ville/update/{id}")
    public City updateCity(@RequestBody CityRequest cityRequest,@PathVariable int id )
    { return cityService.updateCity(cityRequest,id);}

    @DeleteMapping("ville/delete/{id}")
    public ResponseEntity<?> deleteCityById(@PathVariable int id){
        try {

            cityService.deleteCityById(id);

            ApiResponse res = new ApiResponse();
            res.setSuccess(true);
            res.setMessage("Ville supprimé avec succé!");
            return ResponseEntity.ok(res);
        }catch(Exception e) {
            return ResponseEntity.notFound().build().ok("Ville introuvable!");
        }

    }
}
