package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.Country;
import com.helmi.TunningMarket.repositories.CountryRepository;
import com.helmi.TunningMarket.requests.CountryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public Country getCountryById(@PathVariable int id){
        return countryRepository.findById(id); }

    public List<Country> getAllCountry(){
        return countryRepository.findAll();}

    public Country addCountry(@RequestBody CountryRequest countryRequest){
        Country country = new Country();
        country.setCountryname(countryRequest.countryname);
        return countryRepository.save(country);}

    public Country updateCountry(CountryRequest countryRequest,int id){
        Country country = countryRepository.findById(id);
        country.setCountryname(countryRequest.countryname);
        return countryRepository.save(country);
    }
    public void DeleteCountry(int id){
        this.countryRepository.deleteById(id);
    }
}
