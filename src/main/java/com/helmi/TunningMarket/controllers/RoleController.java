package com.helmi.TunningMarket.controllers;


import com.helmi.TunningMarket.entities.Role;
import com.helmi.TunningMarket.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/role/liste")
    List<Role> getRoles(){
        return roleRepository.findAll() ;
    }
}
