package com.agenciavuelos.modules.tripBooking.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.tripBooking.domain.TripBooking;

public interface TripBookingRepository {
    public Optional<TripBooking> findById(int id);

    public List<TripBooking> findAll();

    public int save(TripBooking tripBooking);

    public void update(TripBooking tripBooking);

    public void delete(int id);

    public List<Integer> findBookingsByCustomerId(int id);

    public List<TripBooking> findByCustomerId(int id);

    public List<TripBooking> findByTripId(int id);

    public List<TripBooking> findBookingById(int id);

    public Optional<TripBooking> findTripBookingOfCustomer(int idCustomer, int idTripBooking);

    public List<Customer> findPassengersOfBooking( int idTripBooking);
}