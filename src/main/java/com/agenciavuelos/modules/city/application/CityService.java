package com.agenciavuelos.modules.city.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.city.domain.City;
import com.agenciavuelos.modules.city.infrastructure.CityRepository;
import com.agenciavuelos.modules.country.domain.Country;
import com.agenciavuelos.modules.country.infrastructure.CountryRepository;

public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;


    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    public List<City> findAllCities(){
        return cityRepository.findAll();
    }

    public Optional<City>  findCityById(int id) {
        Optional<City> optionalCity = this.cityRepository.findById(id);
        return optionalCity;

    }

    public void deleteCity(int id){
        this.cityRepository.delete(id);
    }

    public void updateCity(City city){
        this.cityRepository.update(city);
    }

    public void createCity(City city){
        this.cityRepository.save(city);
    }

    public int getCountryId(int id) {
        int idF = -1;
        Optional<Country> foundCountry = countryRepository.findById(id);
        if (foundCountry.isPresent()) {
            Country country = foundCountry.get();
            idF = country.getId();
        }
        return idF;
    }
}