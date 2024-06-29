package com.agenciavuelos.modules.flightConnection.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.flightConnection.application.FlightConnectionService;
import com.agenciavuelos.modules.flightConnection.domain.FlightConnection;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.trip.domain.Trip;

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
        "5. Asignar avion a un vuelo",
        "6. Buscar vuelo"
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
                while(true){
                    this.createNewConnection(); // esto es para crear conexiones de 0. Las conexiones "automaticas" se haran desde el adpatador de admin
                    int breaker = Util.getIntInput(">> Presiona 1 si quieres agregar mas conexiones.\n...o digita cualquier otro numero para salir");

                    if (breaker != 1){
                        break;
                    }
                }
                
                break;
        
            case 2: // ACTUALIZAR
                this.updateFlightConnection();
                break;
            
            case 3: // BUSCAR CONEXIONES POR ID DE VIAJE
                this.searchFlightsByTrip();
                break;

            case 4: // eliminar
                this.deleteFlightConnection();
                break;

            case 5: // ASIGNAR AERONAVE A TRAYEECTORIA
                this.setPlanetoTrip();
                break;
            
            case 6: // buscar vuelo
                this.searchFlightById();
                break;
        }
    }

    /**
     * Establece la información básica para crear una nueva conexión de vuelo.
     * Este método facilita la creación de conexiones directas o indirectas,
     * según los detalles específicos proporcionados.
     * 
     * @param flightConnection El objeto FlightConnection al que se llenarán los datos.
     *                         Debe contener al menos el número de conexión y la matrícula del avión.
     * 
     * @return El objeto FlightConnection con todos los datos completos, listo para ser creado.
     * 
     * NOTA: Una conexión directa se refiere a los detalles adicionales de un vuelo directo.
    */
    public void setInfoAboutNewConnection(FlightConnection flightConnection){
        // numero de conexion
        String newConnectionNumber;
        int nConnectionNumbers;
        do {
            newConnectionNumber = Util.getStringInput(">> Digita el numero del vuelo : ");
            nConnectionNumbers = this.flightConnectionService.verifyConnectionNumber(newConnectionNumber);
            if (nConnectionNumbers != 0){
                Util.showWarning("El numero de vuelo debe ser unico.");
            }
        }while (nConnectionNumbers != 0);

        // matricula del avion
        String newPlanePlates;
        String foundIdString;
        do {
            newPlanePlates = Util.getStringInput(">> Introduzca la matricula del avion"); 
            foundIdString = this.flightConnectionService.getPlanePlate(newPlanePlates);

        }while (foundIdString == "" );

        // creacion del objeto

        
        flightConnection.setConnectionNumber(newConnectionNumber);
        flightConnection.setPlanePlates(newPlanePlates);
        
        this.flightConnectionService.createFlightConnection(flightConnection);


    }

    /**
     * Crea una nueva conexión directa basada en los datos proporcionados en el objeto FlightConnection.
     * 
     * @param un objeto Trip que le proporcione su id 
     */
    public void newDirectConnection(Trip directTrip){ // esta crea conexiones: cuando son remitidas de viajes directos
        // pasa los datos a la conexion
        FlightConnection newDirectTrip = new FlightConnection();
        newDirectTrip.setIdAirport( directTrip.getIdAirportA() );
        newDirectTrip.setIdTrip( directTrip.getId() );

        this.setInfoAboutNewConnection(newDirectTrip);
    }

    /**
     * Crea una nueva conexión de vuelo a partir de cero, solicitando todos los datos necesarios para su creación.
     * 
     * Esta función no recibe parámetros externos, ya que maneja la solicitud de datos internamente.
     * Se asegura de que el trayecto y el aeropuerto estén correctamente identificados antes de proceder con la creación de la conexión.
     */
    public void createNewConnection(){
        
        FlightConnection largeTrip = new FlightConnection();
        
        // id del viaje
        int foundId;
        int newIdTrip;
        newIdTrip = Util.getIntInput(">> Introduzca el id del trayecto al que pertenece esta conexión: "); 
        foundId = this.flightConnectionService.getTripId(newIdTrip);

        if (foundId != -1){

            // id del aeropuerto
            String foundIdString;
            String newIdAirport;
            do {
                newIdAirport = Util.getStringInput(">> Introduzca la identifiacion del aeropuerto"); 
                foundIdString = this.flightConnectionService.getAirportId(newIdAirport);
            }while (foundIdString == "" );

            // seteanodo el objeto
            largeTrip.setIdTrip(newIdTrip);
            largeTrip.setIdAirport(newIdAirport);

            this.setInfoAboutNewConnection(largeTrip);

        } else {
            Util.showWarning("! El viaje no existe.");
        }
    }

    public void updateFlightConnection(){

        int foundId;

        // solicitar viaje 
        int updateIdTrip = Util.getIntInput(">> Introduzca el id del trayecto al que pertenece esta conexión: "); 
        foundId = this.flightConnectionService.getTripId(updateIdTrip);

        if (foundId != -1){
            // id de la escala
            foundId = Util.getIntInput(">> Introduzca el identificador de la escala");
            
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
    }

    public void searchFlightsByTrip(){
        int foundId;
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
    }

    public void deleteFlightConnection(){
        int foundId;
        foundId = Util.getIntInput(">> Introduzca el identificador de la escala");
            Optional<FlightConnection> deleteFlightConnecOptional = this.flightConnectionService.getFlightConnectionById(foundId);

            deleteFlightConnecOptional.ifPresentOrElse(
                spottedFlightConnection -> {

                    // obtener matricula de avion

                    this.flightConnectionService.deleteFlightConnection(spottedFlightConnection.getId());
                }, 
                () -> {
                    Util.showWarning("No se encontró el trayecto o este no existe");
            }
        );
    }

    public void setPlanetoTrip(){
        int foundId;
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
    }

    public void searchFlightById(){
        int foundId;
        foundId = Util.getIntInput(">> Introduzca el identificador de la escala");
            Optional<FlightConnection> optionalFlighConnection = this.flightConnectionService.getFlightConnectionById(foundId);

            optionalFlighConnection.ifPresentOrElse(
                spottedFlightConnection -> {
                    System.out.println(spottedFlightConnection);
                }, 
                () -> {
                    Util.showWarning("No se encontró el vuelo.");
                });

    }
}
    

