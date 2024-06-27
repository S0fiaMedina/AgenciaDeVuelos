package com.agenciavuelos.modules.flightConnection.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.flightConnection.application.FlightConnectionService;
import com.agenciavuelos.modules.flightConnection.domain.FlightConnection;
import com.agenciavuelos.modules.plane.domain.Plane;

public class FlightConnectionConsoleAdapter {

    private final FlightConnectionService flightConnectionService;

    public FlightConnectionConsoleAdapter(FlightConnectionService flightConnectionService) {
        this.flightConnectionService = flightConnectionService;
    }

    String[] flightConnectionOptions = {
        "1. Registrar escala",
        "2. Actualizar escala",
        "3. Consultar escalas de un trayecto",
        "4. Eliminar escala",
        "5. Asignar avion a un vuelo"
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

        int foundId;
        String foundIdString;
    
        switch (selectedOption) {
            

            case 1: // REGISTRAR 
                
                // id del viaje
                int newIdTrip;
                newIdTrip = Util.getIntInput(">> Introduzca el id del trayecto al que pertenece esta conexión: "); 
                foundId = this.flightConnectionService.getTripId(newIdTrip);


                if (foundId != -1){
                
                    // numero de conexion
                    String newConnectionNumber = Util.getStringInput(">> Digita el numero de conexión de la escala: ");

                    // matricula del avion
                    String newPlanePlates;
                    do {
                        newPlanePlates = Util.getStringInput(">> Introduzca la matricula del avion"); 
                        foundIdString = this.flightConnectionService.getPlanePlate(newPlanePlates);
                    }while (foundIdString == "" );


                    // id del aeropuerto
                    String newIdAirport;
                    do {
                        newIdAirport = Util.getStringInput(">> Introduzca la identifiacion del aeropuerto"); 
                        foundIdString = this.flightConnectionService.getAirportId(newIdAirport);
                    }while (foundIdString == "" );

                    // creacion del objeto

                    FlightConnection newFlightConnection = new FlightConnection();
                    newFlightConnection.setConnectionNumber(newConnectionNumber);
                    newFlightConnection.setIdAirport(newIdAirport);
                    newFlightConnection.setIdTrip(newIdTrip);
                    newFlightConnection.setPlanePlates(newPlanePlates);

                    this.flightConnectionService.createFlightConnection(newFlightConnection);

                } else {
                    Util.showWarning("! El viaje no existe.");
                }
                break;
        
            case 2: // ACTUALIZAR

            // solicitar viaje 
            
                int updateIdTrip = Util.getIntInput(">> Introduzca el id del trayecto al que pertenece esta conexión: "); 
                foundId = this.flightConnectionService.getTripId(updateIdTrip);

                if (foundId != -1){
                    // id de la escala
                    foundId = Util.getIntInput(">> Introduzca el identificador de la escala");
                    System.out.println("---------_>" + foundId);
                    Optional<FlightConnection> foundFlightConnection = this.flightConnectionService.getFlightConnectionById(foundId);
                    System.out.println(foundFlightConnection);
                    foundFlightConnection.ifPresentOrElse(
                        spottedFlightConnection -> {
                            String updateIdString;


                            // numero de conexion
                            String updateConnectionNumber = Util.getStringInput(">> Digita el  nuevo numero de conexión de la escala: ");

                            // matricula de avion
                            String updatePlanePlates;
                            do {
                                updatePlanePlates = Util.getStringInput(">> Introduzca la matricula del avion"); 
                                updateIdString = this.flightConnectionService.getPlanePlate(updatePlanePlates);
                            }while (updateIdString == "" );


                            // aeropuerto
                            String updateAirportId;
                            do {
                                updateAirportId = Util.getStringInput(">> Introduzca el identificador del aeropuerto"); 
                                updateIdString = this.flightConnectionService.getAirportId(updateAirportId);
                            }while (updateIdString == "" );

                            spottedFlightConnection.setIdAirport(updateAirportId);
                            spottedFlightConnection.setPlanePlates(updatePlanePlates);
                            spottedFlightConnection.setConnectionNumber(updateConnectionNumber);

                            this.flightConnectionService.updateFlightConnection(spottedFlightConnection);

                        }
                    , 
                        () -> {
                            Util.showWarning("No se encontró la escala con el identificador: " +  updateIdTrip);
                    });
                    

                } else {
                    Util.showWarning("! ERROR: No hay trayectos registrados");
                }
                break;
            
            case 3: // BUSCAR CONEXIONES POR ID DE VIAJE
                 // id del viaje
                int searchIdTrip;
                searchIdTrip = Util.getIntInput(">> Introduzca el id del trayecto al que pertenece esta conexión: "); 
                foundId = this.flightConnectionService.getTripId(searchIdTrip);

                if (foundId != -1){
                    List<FlightConnection> flightConnections = this.flightConnectionService.getAllFlightConnectionsByTrip(foundId);

                    if ( !flightConnections.isEmpty()){
                        flightConnections.forEach(connection -> {
                            System.out.println(connection);
                        });
                    } else {
                        Util.showWarning("No hay escalas registradas para el vuelo");
                    }

                } else{
                    Util.showWarning("No se encontró el viaje");
                }

                
            break;

            case 4: // eliminar
            foundId = Util.getIntInput(">> Introduzca el identificador de la escala");
            Optional<FlightConnection> deleteFlightConnecOptional = this.flightConnectionService.getFlightConnectionById(foundId);

            deleteFlightConnecOptional.ifPresentOrElse(
                spottedFlightConnection -> {

                    // obtener matricula de avion

                    this.flightConnectionService.deleteFlightConnection(spottedFlightConnection.getId());
                }, 
                () -> {
                    Util.showWarning("No se encontró el trayecto o este no existe");
                });
            break;

            case 5: // ASIGNAR AERONAVE A TRAYEECTORIA

            List<Plane> planes = this.flightConnectionService.getAllPlanes();

            if ( !planes.isEmpty() ){
                foundId = Util.getIntInput(">> Introduzca el identificador de la escala");
                Optional<FlightConnection> asignFlightConnectionOptional = this.flightConnectionService.getFlightConnectionById(foundId);

                asignFlightConnectionOptional.ifPresentOrElse(
                    spottedFlightConnection -> {

                        String platePlaneAsign;
                        String planeToAsign;
                            do {
                                planeToAsign = Util.getStringInput(">> Introduzca la matricula del avion\n NOTA: debe ser  unico"); 
                                platePlaneAsign = this.flightConnectionService.getPlanePlate(planeToAsign);
                            }while (platePlaneAsign == "" );

                        this.flightConnectionService.asignPlaneToTrip(spottedFlightConnection.getId(), platePlaneAsign);
                    }, 
                    () -> {
                        Util.showWarning("No se encontró el trayecto o este no existe");
                    });

            } else {
                Util.showSuccess("No hay aviones disponibles");
            }

            break;
        }
    }
}