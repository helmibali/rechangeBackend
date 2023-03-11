package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.City;
import com.helmi.TunningMarket.entities.Country;
import com.helmi.TunningMarket.entities.Delegation;
import com.helmi.TunningMarket.entities.Gouvernorat;
import com.helmi.TunningMarket.repositories.DelegationRepository;
import com.helmi.TunningMarket.repositories.GouvernoratRepository;
import com.helmi.TunningMarket.requests.CityRequest;
import com.helmi.TunningMarket.requests.DelegationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelegationService {
@Autowired
    DelegationRepository delegationRepository;
@Autowired
    GouvernoratRepository gouvernoratRepository;

public List<Delegation> getDelegations(){ return delegationRepository.findAll();};
public Delegation getDelegationById(Long id){ return  delegationRepository.findById(id).get();}

    public Delegation getDelegationPublic(Long id){ return  delegationRepository.findDelegationPublic(id);}

    public List<Delegation> GetDeleGationByGouvernoratId(Long id){return delegationRepository.findByGouvernorat_Id(id);}
    public Delegation saveDelegation(DelegationRequest delegationRequest) {
        Gouvernorat gouvernorat = gouvernoratRepository.findById(delegationRequest.getGouvernorat()).get();
        Delegation delegation = new Delegation();
        delegation.setLibelle(delegationRequest.getLibelle());
        delegation.setGouvernorat(gouvernorat);
        return  delegationRepository.save(delegation) ;
    }
    public void DeleteDelegationById( Long id ){ this.delegationRepository.deleteById(id);  }
}
