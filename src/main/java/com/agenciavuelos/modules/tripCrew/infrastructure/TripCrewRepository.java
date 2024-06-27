package com.agenciavuelos.modules.tripCrew.infrastructure;

import java.util.List;

import com.agenciavuelos.modules.tripCrew.domain.TripCrew;

public interface TripCrewRepository {

    public void addTripulationToTrip(TripCrew tripCrew);

    public List<TripCrew> getTripulationFromTrip(int idTrip);

}