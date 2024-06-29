package com.agenciavuelos.modules.country.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.country.domain.Country;
import com.agenciavuelos.modules.country.infrastructure.CountryRepository;



public class CountryService {
    private final CountryRepository countryRepository;


    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public List<Country> findAllCountries(){
        return countryRepository.findAll();
    }

    public Optional<Country>  findCountryById(int id) {
        Optional<Country> optionalCountry = this.countryRepository.findById(id);
        return optionalCountry;

    }

    public void deleteCountry(int id){
        this.countryRepository.delete(id);
    }

    public void updateCountry(Country country){
        this.countryRepository.update(country);
    }

    public void createCountry(Country country){
        this.countryRepository.save(country);
    }



}