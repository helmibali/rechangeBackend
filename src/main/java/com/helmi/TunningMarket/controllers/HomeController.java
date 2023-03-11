package com.helmi.TunningMarket.controllers;

import com.helmi.TunningMarket.services.CityService;
import com.helmi.TunningMarket.services.ModeleService;
import com.helmi.TunningMarket.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class HomeController {
    @Autowired
    CityService cityService;
    @Autowired
    ModeleService modeleService;
    @Autowired
    ProduitService produitService;

    @GetMapping("sayHello")
    public String sayHello(){
        return "Hello World";
    }
}
