package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.*;
import com.helmi.TunningMarket.repositories.*;
import com.helmi.TunningMarket.requests.CartRequest;
import com.helmi.TunningMarket.requests.CommandeRequest;
import com.helmi.TunningMarket.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeService {
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    PanierRepository panierRepository;
    @Autowired
    UserService userService;
    @Autowired
    DelegationRepository delegationRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;

    public List<Command> findAll(){return commandeRepository.findAllByDateCreation();}

    public Command findCommandeById(long id){return commandeRepository.getById(id);}

    public void deleteCommande(long id){ commandeRepository.deleteById(id);}



    public Command validateCommande(CommandeRequest commandeRequest,Long id){
        Command c = commandeRepository.getById(id);
        c.setValidation(commandeRequest.isValidation());
        c.setAnnulation(commandeRequest.isAnnulation());
        return commandeRepository.save(c);

    }
    public Command saveCart(CommandeRequest commandeRequest) {
        User user = userRepository.findByUsername(commandeRequest.getUser());
        Delegation delegation = delegationRepository.findById(commandeRequest.getDelegation()).get();
        Command c = new Command();
        c.setPrixCommande(commandeRequest.getPrixCommande());
        c.setUser(user);
        c.setDateCreation(commandeRequest.getDateCreation());
        c.setDelegation(delegation);
        c.setLivraison(commandeRequest.getLivraison());
        c.setQty(commandeRequest.getQty());

        c.setAddress(commandeRequest.getAddress());

        c.setCarts(commandeRequest.getCarts()
                .stream()
                .map(carts ->{
                            Cart car = carts;
                            if(car.getId()>0){
                                car = cartRepository.findById(car.getId()).get();
                            }
                            car.getCommands().add(c);
                            return car;
                        }
                ).collect(Collectors.toList()));


        return  commandeRepository.save(c) ;
    }

}
