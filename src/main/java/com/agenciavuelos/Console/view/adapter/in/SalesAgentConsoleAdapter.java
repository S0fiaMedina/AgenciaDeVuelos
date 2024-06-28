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
        "▶ (6) Consultar tipos de documento "
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
        System.out.println(header);
        Util.printOptions(gestionOptions);
        System.out.println(">> Escoja la opcion de su preferencia: ");
        int selectedOption = Util.rangeValidator(1, gestionOptions.length);

        switch (selectedOption) {
            case 1:
                this.salesAgent.getTripCrewConsoleAdapter().searchForTrip();
                break;
        
            case 2:
                // logica de gestion de vuelos
                break;
            
            case 3:
                // logica de gestion de reservas
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
        }

    }
}
