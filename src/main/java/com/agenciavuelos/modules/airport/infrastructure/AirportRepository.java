package com.agenciavuelos.modules.airport.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airport.domain.Airport;

public interface AirportRepository {
    public Optional<Airport> findById(String id);

    public List<Airport> findAll();

    public void save(Airport airport);

    public void update(Airport airport);

    public void delete(String id);
}