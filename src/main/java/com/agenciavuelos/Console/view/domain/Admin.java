package com.agenciavuelos.Console.view.domain;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.modules.airline.adapter.in.AirlineConsoleAdapter;
import com.agenciavuelos.modules.airport.adapter.in.AirportConsoleAdapter;
import com.agenciavuelos.modules.documentType.adapter.in.DocumentTypeConsoleAdapter;
import com.agenciavuelos.modules.employee.adapter.in.EmployeeConsoleAdapter;
import com.agenciavuelos.modules.flightConnection.adapter.in.FlightConnectionConsoleAdapter;
import com.agenciavuelos.modules.flightFare.adapter.in.FlightFareConsoleAdapter;
import com.agenciavuelos.modules.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import com.agenciavuelos.modules.model.adapter.in.ModelConsoleAdapter;
import com.agenciavuelos.modules.plane.adapter.in.PlaneConsoleAdapter;
import com.agenciavuelos.modules.trip.adapter.in.TripConsoleAdapter;
import com.agenciavuelos.modules.trip.adapter.in.TripConsoleAdapterAdmin;
import com.agenciavuelos.modules.tripCrew.adapter.in.TripCrewConsoleAdapter;

public class Admin {

    private final Initializer initializer;
    private final ManufacturerConsoleAdapter manufacturerConsoleAdapter;
    private final ModelConsoleAdapter modelConsoleAdapter;
    private final AirlineConsoleAdapter airlineConsoleAdapter;
    private final EmployeeConsoleAdapter employeeConsoleAdapter;
    private final AirportConsoleAdapter airportConsoleAdapter;
    private final PlaneConsoleAdapter planeConsoleAdapter;
    private final FlightFareConsoleAdapter flightFareConsoleAdapter;
    private final DocumentTypeConsoleAdapter documentTypeConsoleAdapter;
    private final TripConsoleAdapter tripConsoleAdapter;
    private final TripCrewConsoleAdapter tripCrewConsoleAdapter;
    private final TripConsoleAdapterAdmin tripConsoleAdapterAdmin;
    private final FlightConnectionConsoleAdapter flightConnectionConsoleAdapter;

    public Admin(Initializer initializer){
        this.initializer = initializer;
        this.manufacturerConsoleAdapter = initializer.startManufacturerModule();
        this.modelConsoleAdapter = initializer.starModelConsoleAdapter();
        this.airlineConsoleAdapter = initializer.startAirlineConsoleAdapter();
        this.employeeConsoleAdapter = initializer.startEmployeeConsoleAdapter();
        this.airportConsoleAdapter = initializer.startAirportConsoleAdapter();
        this.planeConsoleAdapter = initializer.startPlaneModule();
        this.flightFareConsoleAdapter = initializer.startFlightFareModule();
        this.documentTypeConsoleAdapter = initializer.startDocumentTypeModule();
        this.tripConsoleAdapter = initializer.startTripModule();
        this.tripCrewConsoleAdapter = initializer.startTripCrewConsoleAdapter();
        this.tripConsoleAdapterAdmin = initializer.startTripAdminModule();
        this.flightConnectionConsoleAdapter = initializer.startFlightConnectionModule();

    }

    public Initializer getInitializer() {
        return initializer;
    }

    public ManufacturerConsoleAdapter getManufacturerConsoleAdapter() {
        return manufacturerConsoleAdapter;
    }

    public ModelConsoleAdapter getModelConsoleAdapter() {
        return modelConsoleAdapter;
    }

    public AirlineConsoleAdapter getAirlineConsoleAdapter() {
        return airlineConsoleAdapter;
    }

    public EmployeeConsoleAdapter getEmployeeConsoleAdapter() {
        return employeeConsoleAdapter;
    }

    public AirportConsoleAdapter getAirportConsoleAdapter() {
        return airportConsoleAdapter;
    }

    public PlaneConsoleAdapter getPlaneConsoleAdapter() {
        return planeConsoleAdapter;
    }

    public FlightFareConsoleAdapter getFlightFareConsoleAdapter() {
        return flightFareConsoleAdapter;
    }

    public DocumentTypeConsoleAdapter getDocumentTypeConsoleAdapter() {
        return documentTypeConsoleAdapter;
    }

    public TripConsoleAdapter getTripConsoleAdapter() {
        return tripConsoleAdapter;
    }

    public TripCrewConsoleAdapter getTripCrewConsoleAdapter() {
        return tripCrewConsoleAdapter;
    }

    public TripConsoleAdapterAdmin getTripConsoleAdapterAdmin() {
        return tripConsoleAdapterAdmin;
    }

    public FlightConnectionConsoleAdapter getFlightConnectionConsoleAdapter() {
        return flightConnectionConsoleAdapter;
    }
    
}
