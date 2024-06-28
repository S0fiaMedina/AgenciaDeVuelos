package com.agenciavuelos.modules.tripBooking.adapter.in;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.tripBooking.application.TripBookingService;
import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.tripBookingDetail.application.TripBookingDetailService;
import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;
import com.agenciavuelos.modules.customer.application.CustomerService;
import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.flightFare.application.FlightFareService;
import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.trip.application.TripService;
import com.agenciavuelos.modules.trip.domain.Trip;

public class TripBookingConsoleAdapter {
    private final TripBookingService tripBookingService;
    private final TripBookingDetailService tripBookingDetailService;
    private final TripService tripService;
    private final CustomerService customerService;
    private final FlightFareService flightFareService;

    // lista que contiene las opciones del menu
    private final  String[] tripBookingOptions = { 
        "1. Crear Reserva",
        "2. Consultar Reserva",
        "3. Eliminar Reserva",
        "4. Salir"
    };

    public TripBookingConsoleAdapter(TripBookingService tripBookingService, TripBookingDetailService tripBookingDetailService, TripService tripService, CustomerService customerService, FlightFareService flightFareService) {
        this.tripBookingService = tripBookingService;
        this.tripBookingDetailService = tripBookingDetailService;
        this.tripService = tripService;
        this.customerService = customerService;
        this.flightFareService = flightFareService;
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
        int optionSelected = getChoiceFromUser();
        List<Customer> customers = customerService.findAllCustomers();
        List<Trip> trips = tripService.findAllTrips();
        List<FlightFare> flightFares = flightFareService.findAllFlightFares();

        switch (optionSelected) {
            case 4:
                this.updateTripBooking();
            break;
/*
            case 1: // CREAR
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
                    int idTrip = Util.getIntInput(">> Ingrese el ID del viaje que desea reservar:");
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
                TripBooking tripBooking = new TripBooking(currentDate, idF);
                int idTB = this.tripBookingService.createTripBooking(tripBooking);
                TripBookingDetail tripBookingDetail = new TripBookingDetail(idTB, idCustomer, idFare);
                this.tripBookingDetailService.createTripBookingDetail(tripBookingDetail);

                break;
        
            case 2: // BUSCAR POR ID
                int SearchId;
                int option = Util.getIntInput("""
                    1. Buscar por ID del cliente
                    2. Buscar por ID del vuelo
                    """);
                switch (option) {
                    case 1:
                        SearchId = Util.getIntInput(">> Introduzca el ID del cliente: ");
                        List<TripBooking> foundTripBookingByCustomerList = this.tripBookingService.getTripBookingByCustomer(SearchId);

                        if (foundTripBookingByCustomerList == null || foundTripBookingByCustomerList.isEmpty()) {
                            Util.showWarning("No se encontraron reservas");
                        } else {
                            System.out.println("RESERVAS ENCONTRADAS");
                            foundTripBookingByCustomerList.forEach( booking -> { System.out.println(booking);});
                        }
                        break;
                    case 2:
                        SearchId = Util.getIntInput(">> Introduzca el ID del trayecto: ");
                        List<TripBooking> foundTripBookingByTripList = this.tripBookingService.getTripBookingByTrip(SearchId);
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
               
            case 3: // ELIMINAR
                int deleteId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<TripBooking> tripBookingToDelete = this.tripBookingService.findTripBookingById(deleteId);
                tripBookingToDelete.ifPresentOrElse(
                    spottedTripBooking -> {
                        this.tripBookingService.deleteTripBooking(deleteId);
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o reserva inexistente");
                    }
                );
                break;
         
*/
        }
    }

    String[] optionsToModify = {
        "1 -> Modificar pasajeros. "
    };

    public void updateTripBooking(){
        
        // VALIDA que el cliente tenga reservas
        int updateIdCustomer = Util.getIntInput(">> Introduzca el identificador del cliente: ");
        List<TripBooking> bookings = this.tripBookingService.getTripBookingByCustomer(updateIdCustomer);
        

        if (bookings.isEmpty()){
            Util.showWarning("El cliente no tiene reservas. ");

        } else {

             // obtener el id que el cliente busca
            int updateIdBooking = Util.getIntInput(">> Introduzca el id de la reserva: ");
                
            Optional<TripBooking> optionalTripBooking = bookings.stream()
            .filter(booking -> booking.getId() == updateIdBooking)
            .findAny();
            
            
            // proceso de actualizacion
            optionalTripBooking.ifPresentOrElse(
                spottedTripBooking -> {
                    System.out.println("Reserva encontrada: \n" + spottedTripBooking);

                    System.out.println("--------> OPCIONES PARA MODIFICAR <------");
                    Util.printOptions(optionsToModify);
                    int modifyOption = Util.rangeValidator(1, optionsToModify.length);

                    switch (modifyOption) {
                        case 1: // modificar pasajeros
                            this.updatePassangers();
                            break;
                    
                        default:
                            break;
                    }



                }, 
                () -> {
                    Util.showWarning("La reserva no esta registrada. ");
                });
            }
    }

    public void updatePassangers(){
        while(true){
            
        }
    }
}



            /* case 2: // ACTUALIZAR
                
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
                */