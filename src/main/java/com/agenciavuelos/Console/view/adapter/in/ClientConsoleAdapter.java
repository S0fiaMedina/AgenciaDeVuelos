package com.agenciavuelos.Console.view.adapter.in;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.Console.view.domain.Client;

public class ClientConsoleAdapter {

    private final Client client;
    private final Initializer initializer;
    
    String[] clientOptions = {
        "▶ (1) Buscar Vuelos",
        "▶ (2) Consultar Reserva de Vuelo",
        "▶ (3) Modificar Reserva de Vuelo",
        "▶ (4) Cancelar Reserva de Vuelo",
        "▶ (5) Salir",
    };

    String header = """

    █▀▀ █░░ █ █▀▀ █▄░█ ▀█▀ █▀▀
    █▄▄ █▄▄ █ ██▄ █░▀█ ░█░ ██▄
        """;

    public ClientConsoleAdapter(Initializer initializer){
        this.initializer = initializer;
        this.client = new Client(initializer);
    }

    public void run(){
        boolean breaker = true;
        while (breaker){
            System.out.println(header);
            Util.printOptions(clientOptions);
            System.out.println(">> Digite la opcion de su preferencia: ");
            int optionSelected = Util.rangeValidator(1, clientOptions.length);

            switch (optionSelected) {
                case 1:
                    this.client.getTripBookingConsoleAdapterClient().searchTrip();
                    break;
                case 2:
                    this.client.getTripBookingConsoleAdapterClient().searchTripBooking();
                    break;
                case 3:
                    this.client.getTripBookingConsoleAdapterClient().updateTripBooking();
                    break;
                case 4:
                    this.client.getTripBookingConsoleAdapterClient().cancelTripBooking();
                    break;
                
                default: 
                    System.out.println("Adios. cliente :D");
                    breaker = false;
                    break;
            }
        }
    }
}
