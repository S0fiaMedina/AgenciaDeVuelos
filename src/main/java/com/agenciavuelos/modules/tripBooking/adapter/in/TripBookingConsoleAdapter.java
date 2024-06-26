package com.agenciavuelos.modules.tripBooking.adapter.in;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.tripBooking.application.TripBookingService;
import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;
import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.trip.domain.Trip;

public class TripBookingConsoleAdapter {
    private final TripBookingService tripBookingService;

    // lista que contiene las opciones del menu
    private final  String[] tripBookingOptions = { 
        "1. Crear Reserva",
        "2. Consultar Reserva",
        "3. Eliminar Reserva",
        "4. Salir"  
    };

    public TripBookingConsoleAdapter(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    /**
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
        String currentDate;
        int idCustomer;
        int idFare;
        int idF;
        int idTrip;
        int optionSelected = getChoiceFromUser();
        List<Customer> customers = tripBookingService.findAllCustomers();
        List<Trip> trips = tripBookingService.findAllTrips();
        List<FlightFare> flightFares = tripBookingService.findAllFlightFares();
        switch (optionSelected) {

            case 1: // CREAR
                if (tripBookingService.findAllCustomers().size() == 0 || tripBookingService.findAllTrips().size() == 0) {
                    Util.showWarning("Por favor, registre empleados y vuelos");
                } else {
                    for (int i = 0; i <= customers.size() - 1; i++) {
                        System.out.println(customers.get(i).getId() + " - " + customers.get(i).getName() + " - " + customers.get(i).getDocumentNumber());
                    }
                    do {
                        idCustomer = Util.getIntInput(">> Ingrese el ID del cliente:");
                        idF = tripBookingService.getCustomerId(idCustomer);
                    } while (idF == -1);
                    for (int i = 0; i <= trips.size() - 1; i++) {
                        System.out.println(trips.get(i).getId() + " - " + trips.get(i).getDate() + " - " + trips.get(i).getIdAirportD() + " - " + trips.get(i).getIdAirportA());
                    }
                    do {
                        idTrip = Util.getIntInput(">> Ingrese el ID del viaje que desea reservar:");
                        idF = tripBookingService.getTripId(idTrip);
                    } while (idF == -1);
                    currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    for (int i = 0; i <= flightFares.size() - 1; i++) {
                        System.out.println(flightFares.get(i).getId() + " - " + flightFares.get(i).getDescription() + " - " + flightFares.get(i).getDetails() + " - " + flightFares.get(i).getValue());
                    }
                    do {
                        idFare = Util.getIntInput(">> Ingrese el ID de la tarifa:");
                        idF = tripBookingService.getFlightFareId(idFare);
                    } while (idF == -1);
                    TripBooking tripBooking = new TripBooking(currentDate, idTrip);
                    int idTB = this.tripBookingService.createTripBooking(tripBooking);
                    TripBookingDetail tripBookingDetail = new TripBookingDetail(idTB, idCustomer, idFare);
                    this.tripBookingService.createTripBookingDetail(tripBookingDetail);
                }
                break;
                
            case 2: // BUSCAR POR ID
                int searchId;
                int option = Util.getIntInput("""
                    1. Buscar por ID del cliente
                    2. Buscar por ID del vuelo
                    """);
                switch (option) {
                    case 1:
                        searchId = Util.getIntInput(">> Introduzca el ID del cliente: ");
                        List<TripBooking> foundTripBookingByCustomerList = this.tripBookingService.getTripBookingByCustomer(searchId);
                        if (foundTripBookingByCustomerList == null || foundTripBookingByCustomerList.isEmpty()) {
                            Util.showWarning("No se encontraron reservas");
                        } else {
                            System.out.println("RESERVAS ENCONTRADAS");
                            for (int i = 0; i <= foundTripBookingByCustomerList.size() - 1; i++) {
                                System.out.println(foundTripBookingByCustomerList.get(i).getIdTrip() + " - " + foundTripBookingByCustomerList.get(i).getIdCustomer() + " - " + foundTripBookingByCustomerList.get(i).getNameCustomer() + " - " + foundTripBookingByCustomerList.get(i).getBookingDate() + " - " + foundTripBookingByCustomerList.get(i).getValueFare());
                            }
                        }
                        break;
                    case 2:
                        searchId = Util.getIntInput(">> Introduzca el ID del trayecto: ");
                        List<TripBooking> foundTripBookingByTripList = this.tripBookingService.getTripBookingByTrip(searchId);
                        if (foundTripBookingByTripList == null || foundTripBookingByTripList.isEmpty()) {
                            Util.showWarning("No se encontraron reservas");
                        } else {
                            System.out.println("RESERVAS ENCONTRADAS");
                            for (int i = 0; i <= foundTripBookingByTripList.size() - 1; i++) {
                                System.out.println(foundTripBookingByTripList.get(i).getIdTrip() + " - " + foundTripBookingByTripList.get(i).getIdCustomer() + " - " + foundTripBookingByTripList.get(i).getNameCustomer() + " - " + foundTripBookingByTripList.get(i).getBookingDate() + " - " + foundTripBookingByTripList.get(i).getValueFare());
                            }
                        }
                        break;
                }
                break;
            case 3: // ELIMINAR
                // TODO: hacer funcion de validacion de obj nulos
                int deleteId = Util.getIntInput(">> Introduzca el ID a eliminar: ");
                Optional<TripBooking> tripBookingToDelete = this.tripBookingService.findTripBookingById(deleteId);
                // TODO: hacer funcion de validacion de obj nulos
                tripBookingToDelete.ifPresentOrElse(
                    spottedTripBooking -> {
                        this.tripBookingService.deleteTripBooking(deleteId);
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o reserva inexistente");
                    });
                    break;
        }
    }
}