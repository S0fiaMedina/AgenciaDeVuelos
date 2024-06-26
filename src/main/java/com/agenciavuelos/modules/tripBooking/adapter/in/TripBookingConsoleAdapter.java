package com.agenciavuelos.modules.tripBooking.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.tripBooking.application.TripBookingService;
import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.trip.application.TripService;
import com.agenciavuelos.modules.trip.domain.Trip;

public class TripBookingConsoleAdapter {
    private final TripBookingService tripBookingService;
    private final TripService tripService;

    // lista que contiene las opciones del menu
    private final  String[] tripBookingOptions = { 
        "1. Crear reserva",
        "2. Actualizar reserva",
        "3. Buscar reserva por ID",
        "4. Eliminar reserva",
        "5. Salir"
    };

    public TripBookingConsoleAdapter(TripBookingService tripBookingService, TripService tripService) {
        this.tripBookingService = tripBookingService;
        this.tripService = tripService;
    }

    /**
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE RESERVAS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.tripBookingOptions); 
        return Util.rangeValidator(1, tripBookingOptions.length);
    }

    public void run(){
        String date;
        int idF;
        boolean isCorrect = true;
        int optionSelected = getChoiceFromUser();
        List<Trip> trips = tripService.findAllTrips();
        switch (optionSelected) {

            case 1: // CREAR
                do {
                    date = Util.getStringInput(">> Ingrese la fecha de la reserva (yyyy-MM-dd):");
                    isCorrect = Util.checkDateFormat(date, "yyyy-MM-dd");
                } while (isCorrect == false);
                for (int i = 0; i <= trips.size() - 1; i++) {
                    System.out.println(trips.get(i).getId() + " - " + trips.get(i).getDate());
                }
                do {
                    int idTrip = Util.getIntInput(">> Ingrese el ID del viaje que desea reservar:");
                    idF = tripBookingService.getTripId(idTrip);
                } while (idF == -1);
                TripBooking  tripBooking = new TripBooking(date, idF);
                this.tripBookingService.createTripBooking(tripBooking);
                break;
        
            case 2: // ACTUALIZAR

                List<TripBooking> tripBookings = this.tripBookingService.findAllTripBookings();

                if (tripBookings == null || tripBookings.isEmpty()  )
                    Util.showWarning("No hay reservas registradas");

                else{
                    int idTripBooking = Util.getIntInput(">> Introduzca el código a buscar: ");
                    Optional<TripBooking> optionalTripBooking = this.tripBookingService.findTripBookingById(idTripBooking);

                
                    optionalTripBooking.ifPresentOrElse( 
                        updatedTripBooking -> {
                            String dateA;
                            int idFA;
                            boolean iCorrect = true;
                            System.out.println("Esta es la información actual de la reserva:\n " + updatedTripBooking);
                            do {
                                dateA = Util.getStringInput(">> Ingrese la fecha de la reserva (yyyy-MM-dd):");
                                iCorrect = Util.checkDateFormat(dateA, "yyyy-MM-dd");
                            } while (iCorrect == false);
                            for (int i = 0; i <= trips.size() - 1; i++) {
                                System.out.println(trips.get(i).getId() + " - " + trips.get(i).getDate());
                            }
                            do {
                                int idTrip = Util.getIntInput(">> Ingrese el ID del viaje que desea reservar:");
                                idFA = tripBookingService.getTripId(idTrip);
                            } while (idFA == -1);
                            
                            updatedTripBooking.setBookingDate(dateA);
                            updatedTripBooking.setIdTrip(idFA);

                            this.tripBookingService.updateTripBooking(updatedTripBooking);
                        },
                        
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                int SearchId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<TripBooking> foundTripBooking = this.tripBookingService.findTripBookingById(SearchId);
                
                foundTripBooking.ifPresentOrElse(
                    spottedTripBooking -> { 
                    System.out.println("Esta es la información de la reserva encontrada:\n" + spottedTripBooking);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o reserva inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                int deleteId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<TripBooking> tripBookingToDelete = this.tripBookingService.findTripBookingById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                tripBookingToDelete.ifPresentOrElse(
                    spottedTripBooking -> {
                        this.tripBookingService.deleteTripBooking(deleteId);
                        System.out.println("Reserva eliminada con éxito");
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o reserva inexistente");
                    });
        }
    }
}