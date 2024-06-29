package com.agenciavuelos.modules.flightFare.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.flightFare.infrastructure.FlightFareRepository;

public class FlightFareService {
    private final FlightFareRepository flightFareRepository;

    public FlightFareService(FlightFareRepository flightFareRepository) {
        this.flightFareRepository = flightFareRepository;
    }

    public List<FlightFare> findAllFlightFares(){
        return flightFareRepository.findAll();
    }

    public Optional<FlightFare> findFlightFareById(int id) {
        Optional<FlightFare> optionalFlightFare = this.flightFareRepository.findById(id);
        return optionalFlightFare;
    }

    public void deleteFlightFare(int id){
        this.flightFareRepository.delete(id);
    }

    public void updateFlightFare(FlightFare flightFare){
        this.flightFareRepository.update(flightFare);
    }

    public void createFlightFare(FlightFare flightFare){
        this.flightFareRepository.save(flightFare);
    }
}