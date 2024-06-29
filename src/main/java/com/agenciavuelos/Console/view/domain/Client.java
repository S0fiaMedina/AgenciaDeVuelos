package com.agenciavuelos.Console.view.domain;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.modules.tripBooking.adapter.in.TripBookingConsoleAdapterClient;

public class Client {

    private final Initializer initializer;
    private final TripBookingConsoleAdapterClient tripBookingConsoleAdapterClient;

    public Client(Initializer initializer){
        this.initializer = initializer;
        this.tripBookingConsoleAdapterClient = initializer.startTripBookingClientModule();
    }
    
    public Initializer getInitializer() {
        return initializer;
    }

    public TripBookingConsoleAdapterClient getTripBookingConsoleAdapterClient() {
        return tripBookingConsoleAdapterClient;
    }
}
