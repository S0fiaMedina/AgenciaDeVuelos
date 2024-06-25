package com.agenciavuelos.modules.flightFare.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.flightFare.domain.FlightFare;

public interface FlightFareRepository {
    public Optional<FlightFare> findById(int id);

    public List<FlightFare> findAll();

    public void save(FlightFare flightFare);

    public void update(FlightFare flightFare);

    public void delete(int id);
}