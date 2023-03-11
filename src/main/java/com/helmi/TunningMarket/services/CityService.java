package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.City;
import com.helmi.TunningMarket.entities.Country;
import com.helmi.TunningMarket.repositories.CityRepository;
import com.helmi.TunningMarket.repositories.CountryRepository;
import com.helmi.TunningMarket.requests.CityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CountryRepository countryRepository;

    public CityService() {
    }

    public List<City> getCities(){
        return cityRepository.findAll();
    }
    public City addCity(City city){
        return cityRepository.save(city);
    }

    public City getCity(String cityname){
        return cityRepository.findByCityname(cityname);
    }
    public City getCityById(int id){ return cityRepository.findById(id);}

    public City saveCity(CityRequest cityRequest) {
        Country country = countryRepository.findById(cityRequest.country_id);
        City city = new City();
        city.setCityname(cityRequest.cityname);
        city.setCitycode(cityRequest.citycode);
        city.setCountry(country);
        return  cityRepository.save(city) ;
    }
    public City updateCity(CityRequest cityRequest, int id){
        City city = cityRepository.findById(id);
        Country country = countryRepository.findById(cityRequest.country_id);
        city.setCityname(cityRequest.cityname);
        city.setCitycode(cityRequest.citycode);
        city.setCountry(country);
        return  cityRepository.save(city) ;

    }
    public void deleteCityById( int id ){ this.cityRepository.deleteById(id);  }
}
