package com.agenciavuelos.modules.airline.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airline.domain.Airline;
import com.agenciavuelos.modules.airline.infrastructure.AirlineRepository;

public class AirlineService {
    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<Airline> findAllAirlines(){
        return airlineRepository.findAll();
    }

    public Optional<Airline> findAirlineById(int id) {
        Optional<Airline> optionalAirline = this.airlineRepository.findById(id);
        return optionalAirline;

    }

    public void deleteAirline(int id){
        this.airlineRepository.delete(id);
    }

    public void updateAirline(Airline airline){
        this.airlineRepository.update(airline);
    }

    public void createAirline(Airline airline){
        this.airlineRepository.save(airline);
    }
}