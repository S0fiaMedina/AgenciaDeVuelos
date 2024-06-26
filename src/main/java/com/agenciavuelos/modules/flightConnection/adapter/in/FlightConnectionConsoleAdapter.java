package com.agenciavuelos.modules.flightConnection.adapter.in;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.flightConnection.application.FlightConnectionService;

public class FlightConnectionConsoleAdapter {

    private final FlightConnectionService flightConnection;

    public FlightConnectionConsoleAdapter(FlightConnectionService flightConnection) {
        this.flightConnection = flightConnection;
    }

    String[] flightConnectionOptions = {
        "1. Registrar escala",
        "2. Actualizar escala",
        "3. Consultar escalas de un trayecto",
        "4. Eliminar escala"
    };


    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE ESCALAS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.flightConnectionOptions); 
        return Util.rangeValidator(1, flightConnectionOptions.length);
    }


    public void run(){
        int selectedOption = this.getChoiceFromUser();

        switch (selectedOption) {
            case 1: // REGISTRAR 

                
                
                break;
        
            default:
                break;
        }
    }
    

}