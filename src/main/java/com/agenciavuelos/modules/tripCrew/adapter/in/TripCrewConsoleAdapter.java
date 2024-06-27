package com.agenciavuelos.modules.tripCrew.adapter.in;

import java.util.List;

import com.agenciavuelos.Console.Util;

import com.agenciavuelos.modules.tripCrew.application.TripCrewService;
import com.agenciavuelos.modules.tripCrew.domain.TripCrew;

public class TripCrewConsoleAdapter {
    private final TripCrewService tripCrewService;
    

    private final  String[] tripCrewOptions = { 
        "1. Añadir tripulacion",
        "2. Consultar tripulacion de un trayecto",
        "5. Salir"
    };

    public TripCrewConsoleAdapter(TripCrewService tripCrewService) {
        this.tripCrewService = tripCrewService;
    }

    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE TRIPULACION DE VUELO");
        System.out.println("-------------------------------------");
        Util.printOptions(this.tripCrewOptions); 
        return Util.rangeValidator(1, tripCrewOptions.length);
    }


    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {
            case 1: // ASIGNAR
                int addMore;
                do{
                    // EMPLEADO
                    String newIdFound;
                    String newEmployeeId;
                    do {
                        newEmployeeId = Util.getStringInput(">> Ingrese el id del nuevo tripulante: ");
                        newIdFound = this.tripCrewService.getIdEmployee(newEmployeeId);
                    } while (newIdFound == "");


                    // logica para validar viajes
                    int newIdTrip;
                    int newIdTripFound;
                    do {
                        newIdTrip = Util.getIntInput(">> Ingrese el id del viaje: ");
                        newIdTripFound = this.tripCrewService.getidTrip(newIdTrip);
                    } while (newIdTripFound == -1);

                    this.tripCrewService.addTripulation(new TripCrew(newEmployeeId, newIdTrip));
                    Util.showSuccess("Empleado con el id " + newEmployeeId);

                    addMore = Util.getIntInput(">> Digite 1 si desea asignar mas empleados: \n...o digite otro numero para salir");

                }while(addMore == 1);

                

                break;
        
            case 2:
                int searchIdTrip;
                searchIdTrip = Util.getIntInput(">> Ingrese el id del viaje ");
                List<TripCrew> foundEmployee = this.tripCrewService.getTripulation(searchIdTrip);

                if ( foundEmployee.isEmpty() ){
                    Util.showWarning("El viaje no tiene empleados adjuntos");
                } else {
                    foundEmployee.forEach( tripCrew -> {System.out.println(tripCrew);});
                }
            break;
        }
    }
}