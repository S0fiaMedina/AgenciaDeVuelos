package com.agenciavuelos.modules.airport.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airport.infrastructure.AirportRepository;
import com.agenciavuelos.modules.city.domain.City;
import com.agenciavuelos.modules.city.infrastructure.CityRepository;
import com.agenciavuelos.modules.airport.domain.Airport;

public class AirportService {
    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public AirportService(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }

    public List<Airport> findAllAirports(){
        return airportRepository.findAll();
    }

    public Optional<Airport> findAirportById(String id) {
        Optional<Airport> optionalAirport = this.airportRepository.findById(id);
        return optionalAirport;
    }

    public void deleteAirport(String id){
        this.airportRepository.delete(id);
    }

    public void updateAirport(Airport airport){
        this.airportRepository.update(airport);
    }

    public void createAirport(Airport airport){
        this.airportRepository.save(airport);
    }

    public int getCityId(int id) {
        int idF = -1;
        Optional<City> foundCity = cityRepository.findById(id);
        if (foundCity.isPresent()) {
            City city = foundCity.get();
            idF = city.getId();
        }
        return idF;
    }

    public String checkId(String id) {
        String idF = "";
        Optional<Airport> foundAirport = airportRepository.findById(id);
        if (foundAirport.isPresent()) {
            Airport airport = foundAirport.get();
            idF = airport.getId();
            System.out.println("El ID ya se encuentra registrado");
        }
        return idF;
    }
}