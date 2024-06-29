package com.agenciavuelos.modules.tripCrew.adapter.in;

import java.util.List;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.employee.domain.Employee;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.tripCrew.application.TripCrewService;
import com.agenciavuelos.modules.tripCrew.domain.TripCrew;

public class TripCrewConsoleAdapter {
    private final TripCrewService tripCrewService;
    

    private final  String[] tripCrewOptions = { 
        "1. Añadir tripulacion a un viaje",
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
        System.out.println(">> Escoja la opcion de su preferencia: ");
        return Util.rangeValidator(1, tripCrewOptions.length);
    }


    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {
            case 1: // ASIGNAR
                this.setTripulationToTrip();
                break;
        
            case 2:
                this.searchForTrip();
            break;
        }
    }

    public void setTripulationToTrip(){
        List<Employee> employees = this.tripCrewService.getAllEmployees();
        if (  employees == null || employees.isEmpty() ){
            Util.showWarning("No hay empleados registrados. ");

        } else{
            // logica para validar viajes
            int newIdTrip;
            int newIdTripFound;
            List<Trip> trips = this.tripCrewService.getAllTrips();
           
            System.out.println("------------> TRAYECTOS DISPONIBLES <-------------");
            trips.forEach( trip -> {System.out.println(trip);});

            newIdTrip = Util.getIntInput(">> Ingrese el id del viaje: ");
            newIdTripFound = this.tripCrewService.getidTrip(newIdTrip);

            if (newIdTripFound == -1){
                Util.showWarning("No existe el trayecto con el id: " + newIdTrip);

            } else {
                int addMore;
                do{
                    // EMPLEADO
                    String newIdFound;
                    String newEmployeeId;

                    do {
                        newEmployeeId = Util.getStringInput(">> Ingrese el id del nuevo tripulante: ");
                        newIdFound = this.tripCrewService.getIdEmployee(newEmployeeId);
                    } while (newIdFound == "");


                    this.tripCrewService.addTripulation(new TripCrew(newEmployeeId, newIdTrip));
                    Util.showSuccess("Empleado con el id " + newEmployeeId + " ha sido agregado");

                    addMore = Util.getIntInput(">> Digite 1 si desea asignar mas empleados: \n...o digite otro numero para salir");

                }while(addMore == 1);
            }
        }
    }

    public void searchForTrip(){
        int searchIdTrip;
        searchIdTrip = Util.getIntInput(">> Ingrese el id del viaje ");

        List<TripCrew> foundEmployee = this.tripCrewService.getTripulation(searchIdTrip);

        if ( foundEmployee.isEmpty() ){
            Util.showWarning("El viaje no tiene empleados adjuntos o no existe");
        } else {
            System.out.println("------------> TRIPULANTES DEL VUELO <-------------");
            foundEmployee.forEach( tripCrew -> {System.out.println(tripCrew);});
        }
    }
}