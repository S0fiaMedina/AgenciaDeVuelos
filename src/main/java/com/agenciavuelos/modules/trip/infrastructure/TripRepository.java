package com.agenciavuelos.modules.trip.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.trip.domain.Trip;

public interface TripRepository {
    public List<Trip> searchTrips(String nameCityD, String nameCityA, String departureDate);

    public Optional<Trip> findById(int id);

    public List<Trip> findAll();

    public int save(Trip trip);

    public void update(Trip trip);

    public void delete(int id);
}