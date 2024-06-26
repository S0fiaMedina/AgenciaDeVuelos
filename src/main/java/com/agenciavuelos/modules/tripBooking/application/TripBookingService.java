package com.agenciavuelos.modules.tripBooking.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.trip.infrastructure.TripRepository;
import com.agenciavuelos.modules.tripBooking.infrastructure.TripBookingRepository;

public class TripBookingService {
    private final TripBookingRepository tripBookingRepository;
    private final TripRepository tripRepository;

    public TripBookingService(TripBookingRepository tripBookingRepository, TripRepository tripRepository) {
        this.tripBookingRepository = tripBookingRepository;
        this.tripRepository = tripRepository;
    }

    public List<TripBooking> findAllTripBookings(){
        return tripBookingRepository.findAll();
    }

    public Optional<TripBooking>  findTripBookingById(int id) {
        Optional<TripBooking> optionalTripBooking = this.tripBookingRepository.findById(id);
        return optionalTripBooking;

    }

    public void deleteTripBooking(int id){
        this.tripBookingRepository.delete(id);
    }

    public void updateTripBooking(TripBooking tripBooking){
        this.tripBookingRepository.update(tripBooking);
    }

    public void createTripBooking(TripBooking tripBooking){
        this.tripBookingRepository.save(tripBooking);
    }

    public int getTripId(int id) {
        int idF = -1;
        Optional<Trip> foundTrip = tripRepository.findById(id);
        if (foundTrip.isPresent()) {
            Trip trip = foundTrip.get();
            idF = trip.getId();
        }
        return idF;
    }
}