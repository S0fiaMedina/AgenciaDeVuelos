package com.agenciavuelos.modules.trip.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.trip.application.TripService;
import com.agenciavuelos.modules.trip.domain.Trip;

public class TripConsoleAdapterAdmin {
    private final TripService tripService;

     
    private final String[] tripAdminOptions = {
        "1. Consultar trayecto",
        "2. Actualizar trayecto",
        "3. Eliminar trayecto"
    };

    public TripConsoleAdapterAdmin(TripService tripService){
        this.tripService = tripService;
    }

    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE TRAYECTOS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.tripAdminOptions); 
        System.out.println(">> Escoja la opcion de su preferencia: ");
        return Util.rangeValidator(1, tripAdminOptions.length);
    }

    public void run(){
        int op = this.getChoiceFromUser();
        switch (op) {
            case 1:
                this.getATripById();
                break;
            
            case 2:
                this.updateTrip();
                break;
            
            case 3:
                this.deleteTrip();
                break;
        }
    }

    /**
     * Esta funcion crea viajes
     * @return el id del nuevo trayecto ccreado
    */
    public Trip createTrip(){

        boolean isCorrect;
        String date;
        do {
            date = Util.getStringInput(">> Ingrese la fecha del vuelo (yyyy-MM-dd):");
            isCorrect = Util.checkDateFormat(date, "yyyy-MM-dd");
        } while (isCorrect == false);

        Double price;
        price = Util.getDoubleInput(">> Ingrese el precio del vuelo:");

        // obtener aeropuerto de salida
        String idAirportDeparture;
        String idSD = "";
        do {
            idAirportDeparture = Util.getStringInput(">> Ingrese el ID del aeropuerto de salida: ");
            idSD = tripService.getAirportId(idAirportDeparture);
        } while (idSD == "");

        // obtener aeropuerto de aterrizaje
        String idAirportArrival;
        String idSA = "";
        do {
            idAirportArrival = Util.getStringInput(">> Ingrese el ID del aeropuerto de llegada: ");
            idSA = tripService.getAirportId(idAirportArrival);
            if (idSA.equals(idSD)) {
                Util.showWarning("El aeropuerto de llegada no puede ser el mismo que el de salida");
                idSA = "";
            }
        } while (idSA == "");

        

        Trip trip = new Trip(date, price, idSD, idSA);
        trip.setId(this.tripService.createTrip(trip)); // pone el id obtenido en el obj para que sea enviado al adaptador de conexiones  
        return  trip;
    }

    // obtiene la informacion (BASICA) de viajes. 
    public TripService getATripById() {
        int id = Util.getIntInput(">> Introduzca el ID a buscar: ");
        Optional<Trip> foundTrip = this.tripService.findTripById(id);
        
        foundTrip.ifPresentOrElse(
            spottedTrip -> {
            System.out.println("Esta es la información del vuelo encontrado:\n" + spottedTrip.getDate() + " - " + spottedTrip.getIdAirportD() + " - " + spottedTrip.getIdAirportA());
            },
            ()-> {
                Util.showWarning("ID no encontrado o vuelo inexistente");
            }
        
        );
        return tripService;
    }

    public void updateTrip(){
        List<Trip> trips = this.tripService.findAllTrips();

        if (trips == null || trips.isEmpty()){
            Util.showWarning("No hay vuelos registrados");
        } 
        else{
        int id = Util.getIntInput(">> Introduzca el ID a buscar: ");
        Optional<Trip> optionalTrip = this.tripService.findTripById(id);

        optionalTrip.ifPresentOrElse(
            updatedTrip -> {
                String dateA;
                Double priceA;
                String idAirportDA;
                String idAirportAA;
                String idSDA = "";
                String idSAA = "";
                boolean iCorrect = true;
                System.out.println("Esta es la información actual del vuelo:\n " + updatedTrip.getDate() + " - " + updatedTrip.getIdAirportD() + " - " + updatedTrip.getIdAirportA());
                do {
                    dateA = Util.getStringInput(">> Ingrese la fecha del vuelo (yyyy-MM-dd):");
                    iCorrect = Util.checkDateFormat(dateA, "yyyy-MM-dd");
                } while (iCorrect == false);
                priceA = Util.getDoubleInput(">> Ingrese el precio del vuelo:");
                do {
                    idAirportDA = Util.getStringInput(">> Ingrese el ID del aeropuerto de salida: ");
                    idSDA = tripService.getAirportId(idAirportDA);
                } while (idSDA == "");
                do {
                    idAirportAA= Util.getStringInput(">> Ingrese el ID del aeropuerto de llegada: ");
                    idSAA = tripService.getAirportId(idAirportAA);
                    if (idSAA.equals(idSDA)) {
                        Util.showWarning("El aeropuerto de llegada no puede ser el mismo que el de salida");
                        idSAA = "";
                    }
                } while (idSAA == "");

                updatedTrip.setDate(dateA);
                updatedTrip.setPrice(priceA);
                updatedTrip.setIdAirportD(idSDA);
                updatedTrip.setIdAirportA(idSAA);

                this.tripService.updateTrip(updatedTrip);
            },
            () -> {
                Util.showWarning("ID no encontrado");
            });
        }
    }

    public void deleteTrip(){
        int deleteId = Util.getIntInput(">> Introduzca el ID a buscar: ");
        Optional<Trip> tripToDelete = this.tripService.findTripById(deleteId);
        // TODO: hacer funcion de validacion de obj nulos
        tripToDelete.ifPresentOrElse(
            spottedTrip -> {
                this.tripService.deleteTrip(deleteId);
            },
            () -> {
                Util.showWarning("ID no encontrado o vuelo inexistente");
        });
                    
    }
}
