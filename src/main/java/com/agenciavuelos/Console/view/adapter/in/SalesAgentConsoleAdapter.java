package com.agenciavuelos.Console.view.adapter.in;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.Console.view.domain.SalesAgent;

public class SalesAgentConsoleAdapter {
    private final Initializer initializer;
    private final SalesAgent salesAgent;

    String[] gestionOptions = {
        "▶ (1) Consultar asignaciones de tripulaciones de vuelo",
        "▶ (2) Gestión Vuelos",
        "▶ (3) Gestión Reservas",
        "▶ (4) Gestión Clientes",
        "▶ (5) Consultar Tarifas de Vuelo",
        "▶ (6) Consultar tipos de documento ",
        "▶ (7) Salir"
    };

    String header = """

    ▄▀█ █▀▀ █▀▀ █▄░█ ▀█▀ █▀▀   █▀▄ █▀▀   █░█ █▀▀ █▄░█ ▀█▀ ▄▀█ █▀
    █▀█ █▄█ ██▄ █░▀█ ░█░ ██▄   █▄▀ ██▄   ▀▄▀ ██▄ █░▀█ ░█░ █▀█ ▄█                                                                 
    """;

    public SalesAgentConsoleAdapter(Initializer initializer){
        this.initializer = initializer;
        this.salesAgent = new SalesAgent(initializer);
    }

    public void run(){
        boolean breaker = true;
        while (breaker){
            System.out.println(header);
            Util.printOptions(gestionOptions);
            System.out.println(">> Escoja la opcion de su preferencia: ");
            int selectedOption = Util.rangeValidator(1, gestionOptions.length);

        switch (selectedOption) {
            case 1:
                this.salesAgent.getTripCrewConsoleAdapter().searchForTrip();
                break;
        
            case 2:
                this.flightHandler();
                break;
            
            case 3:
                this.salesAgent.getTripBookingConsoleAdapter().run();
                break;

            case 4:
                this.salesAgent.getCustomerConsoleAdapter().run();
                break;
            
            case 5:
                this.salesAgent.getFlightFareConsoleAdapter().searchFlightFare();
                break;
            
            case 6:
                this.salesAgent.getDocumentTypeConsoleAdapter().searchDocumentDocumentType();
            break;

            default: 
                System.out.println("Adios, agente de venta$$");
                breaker = false;
            break;
        }

        }
        

    }

    public void flightHandler(){
        String[] options = {
            "1. Consultar vuelo.",
            "2. Consultar escalas de un trayecto.",
            "3. Salir"
        };

        System.out.println("********* GESTION DE VUELOS **********");
        Util.printOptions(options);
        int op = Util.getIntInput(">> Seleccione la opcion de su preferencia: ");

        switch (op) {
            case 1:
                this.salesAgent.getFlightConnectionConsoleAdapter().searchFlightById();
                break;
        
            case 2:
                this.salesAgent.getFlightConnectionConsoleAdapter().searchFlightsByTrip();
                break;
            default:
                break;
        }

    }
}
