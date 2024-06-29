package com.agenciavuelos.Console.view.domain;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.modules.customer.adapter.in.CustomerConsoleAdapter;
import com.agenciavuelos.modules.documentType.adapter.in.DocumentTypeConsoleAdapter;
import com.agenciavuelos.modules.flightConnection.adapter.in.FlightConnectionConsoleAdapter;
import com.agenciavuelos.modules.flightFare.adapter.in.FlightFareConsoleAdapter;
import com.agenciavuelos.modules.tripBooking.adapter.in.TripBookingConsoleAdapter;
import com.agenciavuelos.modules.tripCrew.adapter.in.TripCrewConsoleAdapter;

public class SalesAgent {
    private final Initializer initializer;
    private final TripCrewConsoleAdapter tripCrewConsoleAdapter;
    private final  FlightConnectionConsoleAdapter flightConnectionConsoleAdapter;
    private final TripBookingConsoleAdapter tripBookingConsoleAdapter;
    private final CustomerConsoleAdapter customerConsoleAdapter;
    private final FlightFareConsoleAdapter flightFareConsoleAdapter;
    private final DocumentTypeConsoleAdapter documentTypeConsoleAdapter;


    public SalesAgent(Initializer initializer){
        this.initializer = initializer;
        this.tripBookingConsoleAdapter = initializer.startTripBookingModule();
        this.flightConnectionConsoleAdapter = initializer.startFlightConnectionModule();
        this.tripCrewConsoleAdapter = initializer.startTripCrewConsoleAdapter();
        this.customerConsoleAdapter = initializer.startCustomerModule();
        this.documentTypeConsoleAdapter = initializer.startDocumentTypeModule();
        this.flightFareConsoleAdapter = initializer.startFlightFareModule();

    }


    public Initializer getInitializer() {
        return initializer;
    }


    public TripCrewConsoleAdapter getTripCrewConsoleAdapter() {
        return tripCrewConsoleAdapter;
    }


    public FlightConnectionConsoleAdapter getFlightConnectionConsoleAdapter() {
        return flightConnectionConsoleAdapter;
    }


    public TripBookingConsoleAdapter getTripBookingConsoleAdapter() {
        return tripBookingConsoleAdapter;
    }


    public CustomerConsoleAdapter getCustomerConsoleAdapter() {
        return customerConsoleAdapter;
    }


    public FlightFareConsoleAdapter getFlightFareConsoleAdapter() {
        return flightFareConsoleAdapter;
    }


    public DocumentTypeConsoleAdapter getDocumentTypeConsoleAdapter() {
        return documentTypeConsoleAdapter;
    }

}
