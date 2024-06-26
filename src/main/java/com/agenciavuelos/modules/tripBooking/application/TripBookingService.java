package com.agenciavuelos.modules.tripBooking.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.customer.infrastructure.CustomerRepository;
import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.flightFare.infrastructure.FlightFareRepository;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.trip.infrastructure.TripRepository;
import com.agenciavuelos.modules.tripBooking.infrastructure.TripBookingRepository;

public class TripBookingService {
    private final TripBookingRepository tripBookingRepository;
    private final TripRepository tripRepository;
    private final CustomerRepository customerRepository;
    private final FlightFareRepository flightFareRepository;

    public TripBookingService(TripBookingRepository tripBookingRepository, TripRepository tripRepository, CustomerRepository customerRepository, FlightFareRepository flightFareRepository) {
        this.tripBookingRepository = tripBookingRepository;
        this.tripRepository = tripRepository;
        this.customerRepository = customerRepository;
        this.flightFareRepository = flightFareRepository;
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

    public int createTripBooking(TripBooking tripBooking){
        return this.tripBookingRepository.save(tripBooking);
    }

    public List<TripBooking> getTripBookingByCustomer(int id) {
        return this.tripBookingRepository.findByCustomerId(id);
    }

    public List<TripBooking> getTripBookingByTrip(int id) {
        return this.tripBookingRepository.findByTripId(id);
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

    public int getCustomerId(int id) {
        int idF = -1;
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isPresent()) {
            Customer customer = foundCustomer.get();
            idF = customer.getId();
        }
        return idF;
    }

    public int getFlightFareId(int id) {
        int idF = -1;
        Optional<FlightFare> foundFlightFare = flightFareRepository.findById(id);
        if (foundFlightFare.isPresent()) {
            FlightFare flightFare = foundFlightFare.get();
            idF = flightFare.getId();
        }
        return idF;
    }
}