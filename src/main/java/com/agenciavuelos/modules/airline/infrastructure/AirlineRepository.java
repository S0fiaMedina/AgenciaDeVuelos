package com.agenciavuelos.modules.airline.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airline.domain.Airline;

public interface AirlineRepository {
    public Optional<Airline> findById(int id);

    public List<Airline> findAll();

    public void save(Airline airline);

    public void update(Airline airline);

    public void delete(int id);    
}