package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.Gouvernorat;
import com.helmi.TunningMarket.entities.Marque;
import com.helmi.TunningMarket.repositories.GouvernoratRepository;
import com.helmi.TunningMarket.requests.GouvernoratRequest;
import com.helmi.TunningMarket.requests.MarqueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GouvernoratService {

    @Autowired
    GouvernoratRepository gouvernoratRepository;

    public List<Gouvernorat> getGouvernorat(){
        return gouvernoratRepository.findAll();
    }
    public Gouvernorat getGouvernoratById(Long id){ return gouvernoratRepository.findById(id).get();}

    public Gouvernorat saveGouvernorat(GouvernoratRequest gouvernoratRequest) {
        Gouvernorat gouvernorat = new Gouvernorat();
        gouvernorat.setLibelle(gouvernoratRequest.getLibelle());
        return  gouvernoratRepository.save(gouvernorat);
    }
    public Gouvernorat updateGouvernorat(GouvernoratRequest gouvernoratRequest, Long id) {
        Gouvernorat gouvernorat = gouvernoratRepository.findById(id).get();
        gouvernorat.setLibelle(gouvernoratRequest.getLibelle());
        return  gouvernoratRepository.save(gouvernorat);
    }

    public void DeleteGouvernoratById( Long id ){ this.gouvernoratRepository.deleteById(id);  }
}
