package com.agenciavuelos.modules.flightConnection.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.flightConnection.domain.FlightConnection;



public interface FlightConnectionRepository {

    public Optional<FlightConnection> findById(int id);

    public List<FlightConnection> findAllByTrip(int idTrip);

    public void setPlaneToTrip (int idFlightConnection, String planePlate );

    public void save(FlightConnection flightConnection);

    public void update(FlightConnection flightConnection);

    public void delete(int id);

}