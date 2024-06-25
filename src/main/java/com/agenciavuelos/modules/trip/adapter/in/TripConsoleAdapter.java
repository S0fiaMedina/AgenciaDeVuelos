package com.agenciavuelos.modules.trip.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.trip.application.TripService;
import com.agenciavuelos.modules.trip.domain.Trip;

public class TripConsoleAdapter {
    private final TripService tripService;

    // lista que contiene las opciones del menu
    private final  String[] tripOptions = { 
        "1. Crear vuelo",
        "2. Actualizar vuelo",
        "3. Buscar vuelo por ID",
        "4. Eliminar vuelo",
        "5. Salir"
    };

    public TripConsoleAdapter(TripService tripService) {
        this.tripService = tripService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE VUELO");
        System.out.println("-------------------------------------");
        Util.printOptions(this.tripOptions); 
        return Util.rangeValidator(1, tripOptions.length);
    }

    public void run(){
        boolean isCorrect = true;
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR
                String date;
                Double price;
                String idAirportD;
                String idAirportA;
                String idSD = "";
                String idSA = "";
                do {
                    date = Util.getStringInput(">> Ingrese la fecha del vuelo (yyyy-MM-dd):");
                    isCorrect = Util.checkDateFormat(date, "yyyy-MM-dd");
                } while (isCorrect == false);
                price = Util.getDoubleInput(">> Ingrese el precio del vuelo:");
                do {
                    idAirportD = Util.getStringInput(">> Ingrese el ID del aeropuerto de salida: ");
                    idSD = tripService.getAirportId(idAirportD);
                } while (idSD == "");
                do {
                    idAirportA = Util.getStringInput(">> Ingrese el ID del aeropuerto de llegada: ");
                    idSA = tripService.getAirportId(idAirportA);
                    if (idSA.equals(idSD)) {
                        Util.showWarning("El aeropuerto de llegada no puede ser el mismo que el de salida");
                        idSA = "";
                    }
                } while (idSA == "");
                Trip trip = new Trip(date, price, idSD, idSA);
                this.tripService.createTrip(trip);
                break;
        
            case 2: // ACTUALIZAR
                List<Trip> trips = this.tripService.findAllTrips();
                // Aqui se podria colocar una funcion para imprimir los tripos, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (trips == null || trips.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay vuelos registrados");

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
                            System.out.println("Esta es la información actual del vuelo:\n " + updatedTrip);
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

                        // Acción si el fabricante no está presente (ID no encontrado)
                        () -> {
                            Util.showWarning("ID no encontrado");
                        });
                    }
                break;
            
            case 3: // BUSCAR POR ID

                int id = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<Trip> foundTrip = this.tripService.findTripById(id);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundTrip.ifPresentOrElse(
                    spottedTrip -> { // Si el fabricante fue encontrado...
                    System.out.println("Esta es la información del vuelo encontrado:\n" + spottedTrip);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o vuelo inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                int deleteId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<Trip> tripToDelete = this.tripService.findTripById(deleteId);
                // Utilizar ifPresent para manejar el caso cuando el fabricante está presente
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
}