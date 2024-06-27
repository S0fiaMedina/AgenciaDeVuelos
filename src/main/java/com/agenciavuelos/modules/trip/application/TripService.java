package com.agenciavuelos.modules.trip.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airport.domain.Airport;
import com.agenciavuelos.modules.airport.infrastructure.AirportRepository;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.trip.infrastructure.TripRepository;

public class TripService {
    private final TripRepository tripRepository;
    private final AirportRepository airportRepository;

    public TripService(TripRepository tripRepository, AirportRepository airportRepository) {
        this.tripRepository = tripRepository;
        this.airportRepository = airportRepository;
    }

    public List<Trip> searchTrips(String nameCityD, String nameCityA, String departureDate) {
        return tripRepository.searchTrips(nameCityD, nameCityA, departureDate);
    }

    public List<Trip> findAllTrips(){
        return tripRepository.findAll();
    }

    public Optional<Trip> findTripById(int id) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        return optionalTrip;
    }

    public void deleteTrip(int id){
        this.tripRepository.delete(id);
    }

    public void updateTrip(Trip trip){
        this.tripRepository.update(trip);
    }

    public void createTrip(Trip trip){
        this.tripRepository.save(trip);
    }
    
    public TripRepository getTripRepositoryById() {
        return this.tripRepository;
    }

    public String getAirportId(String id) {
        String idF = "";
        Optional<Airport> foundAirport = airportRepository.findById(id);
        if (foundAirport.isPresent()) {
            Airport airport = foundAirport.get();
            idF = airport.getId();
        }
        return idF;
    }
}