package com.agenciavuelos.modules.flightConnection.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airport.domain.Airport;
import com.agenciavuelos.modules.airport.infrastructure.AirportRepository;
import com.agenciavuelos.modules.flightConnection.domain.FlightConnection;
import com.agenciavuelos.modules.flightConnection.infrastructure.FlightConnectionRepository;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.trip.infrastructure.TripRepository;

public class FlightConnectionService {

    private final TripRepository tripRepository;
    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;
    private final FlightConnectionRepository flightConnectionRepository;


    public FlightConnectionService(TripRepository tripRepository, AirportRepository airportRepository,
    PlaneRepository planeRepository, FlightConnectionRepository flightConnectionRepository) {

        this.tripRepository = tripRepository;
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
        this.flightConnectionRepository = flightConnectionRepository;
    }


    public Optional<FlightConnection> getFlightConnectionById(int id){
        return this.flightConnectionRepository.findById(id);
    }

    public List<FlightConnection> getAllFlightConnectionsByTrip(int id){
        return this.flightConnectionRepository.findAllByTrip(id);
    }

    public void createFlightConnection(FlightConnection flightConnection){
        this.flightConnectionRepository.save(flightConnection);
    }

    public void updateFlightConnection(FlightConnection flightConnection){
        this.flightConnectionRepository.update(flightConnection);
    }

    public void deleteFlightConnection(int id){
        this.flightConnectionRepository.delete(id);
    }

    public void asignPlaneToTrip(int idFlightConnection, String plate){
        this.flightConnectionRepository.setPlaneToTrip(idFlightConnection, plate);
    }

    // verificacion avion

    public String getPlanePlate(String plate){
        String foundPlate = "";
        Optional<Plane> foundPlane = planeRepository.findById(plate);

        if (foundPlane.isPresent()) {
            Plane plane = foundPlane.get();
            foundPlate = plane.getPlates();
        }
        return foundPlate;
    }

    // verificacion avion

    public String getAirportId(String id){
        String foundId = "";
        Optional<Airport> foundAirport = airportRepository.findById(id);

        if (foundAirport.isPresent()) {
            Airport airport = foundAirport.get();
            foundId = airport.getId();
        }
        return foundId;
    }


    // verificacion aeropuerto
    public int getTripId(int id){
        int foundId = -1;
        Optional<Trip> foundTrip = tripRepository.findById(id);
        

        if (foundTrip.isPresent()) {
            Trip trip = foundTrip.get();
            foundId = trip.getId();
    
        }
        return foundId;
    }

    // traer todos loa aviones
    public List<Plane> getAllPlanes(){
        return this.planeRepository.findAll();
    }


    
}