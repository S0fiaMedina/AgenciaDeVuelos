package com.agenciavuelos.modules.trip.adapter.in;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.customer.application.CustomerService;
import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.documentType.domain.DocumentType;
import com.agenciavuelos.modules.flightConnection.application.FlightConnectionService;
import com.agenciavuelos.modules.flightFare.application.FlightFareService;
import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.payment.application.PaymentService;
import com.agenciavuelos.modules.payment.domain.Payment;
import com.agenciavuelos.modules.paymentForm.application.PaymentFormService;
import com.agenciavuelos.modules.paymentForm.domain.PaymentForm;
import com.agenciavuelos.modules.plane.application.PlaneService;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.trip.application.TripService;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.tripBooking.application.TripBookingService;
import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.tripBookingDetail.application.TripBookingDetailService;
import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;




public class TripConsoleAdapter {
    private final TripService tripService;
    private final FlightConnectionService flightConnectionService;
    private final TripBookingService tripBookingService;
    private final TripBookingDetailService tripBookingDetailService;
    private final CustomerService customerService;
    private final PlaneService planeService;
    private final FlightFareService flightFareService;
    private final PaymentService paymentService;
    private final PaymentFormService paymentFormService;

    private final  String[] tripOptions = { 
        "1. Buscar Vuelo", // ESTO ES PARA CLIENTE (RESERVA DE VUELO)
        "2. Salir"
    };

    public TripConsoleAdapter(TripService tripService, FlightConnectionService flightConnectionService, TripBookingService tripBookingService, TripBookingDetailService tripBookingDetailService, CustomerService customerService, PlaneService planeService, FlightFareService flightFareService, PaymentService paymentService, PaymentFormService paymentFormService) {
        this.tripService = tripService;
        this.flightConnectionService = flightConnectionService;
        this.tripBookingService = tripBookingService;
        this.tripBookingDetailService = tripBookingDetailService;
        this.customerService = customerService;
        this.planeService = planeService;
        this.flightFareService = flightFareService;
        this.paymentService = paymentService;
        this.paymentFormService = paymentFormService;
    }

    /**
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("BUSQUEDA DE VUELO");
        System.out.println("-------------------------------------");
        Util.printOptions(this.tripOptions); 
        return Util.rangeValidator(1, tripOptions.length);
    }

    public void run(){
        boolean isCorrect = true;
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {
                
            case 1:

                int documentNumber = Util.getIntInput(">> Ingrese su número de documento:");
                Optional<Customer> foundC = this.customerService.findByDocumentNumber(documentNumber);
                
                foundC.ifPresentOrElse(
                    spottedCustomer -> {
                        Boolean iCorrect = true;
                        String dateD;
                        List<Integer> flightsFound = new ArrayList<>();
                        int idSpottedCustomer = spottedCustomer.getId();
                        String nameCityD = Util.getStringInput(">> Ingrese la ciudad de origen: ");
                        String nameCityA = Util.getStringInput(">> Ingrese la ciudad de destino: ");
                        do {
                            dateD = Util.getStringInput(">> Ingrese la fecha del vuelo (yyyy-MM-dd):");
                            iCorrect = Util.checkDateFormat(dateD, "yyyy-MM-dd");
                        } while (iCorrect == false);

                        List<Trip> foundTripsList = this.tripService.searchTrips(nameCityD, nameCityA, dateD);
                        if (foundTripsList == null || foundTripsList.isEmpty()) {
                            Util.showWarning("No se encontraron vuelos");
                        } else {
                            System.out.println("VUELOS ENCONTRADOS");
                            for (int i = 0; i <= foundTripsList.size() - 1; i++) {
                                System.out.println(foundTripsList.get(i).getId() + " - " + foundTripsList.get(i).getDate() + " - " + foundTripsList.get(i).getNameCityD() + " - " + foundTripsList.get(i).getNameCityA());
                                flightsFound.add(foundTripsList.get(i).getId());
                            }
                            int optionOne = Util.getIntInput("""

                            1. Seleccionar Vuelo
                            2. Salir
                            """);
                            switch (optionOne) {
                                case 1:
                                    int choice;
                                    do {
                                        choice = Util.getIntInput(">> Ingrese el ID del vuelo que desea seleccionar: ");
                                    } while (!flightsFound.contains(choice));
                                    
                                    Optional<Trip> foundT = this.tripService.findTripById(choice);
                                    
                                    foundT.ifPresentOrElse(
                                        spottedTrip -> {
                                            // SE DEBE MOSTRAR LOS DATOS DE ESCALAS
                                            System.out.println("Esta es la información del vuelo seleccionado:\n" + spottedTrip.getDate() + " - " + spottedTrip.getNameCityD() + " - " + spottedTrip.getNameCityA() + " - " + spottedTrip.getPrice());

                                            Optional<Plane> tripPlane = this.planeService.findByTrip(foundT.get().getId());
                                            tripPlane.ifPresentOrElse(
                                                spottedPlane -> {
                                                    int idF;
                                                    int idFare;
                                                    int idFound;
                                                    int capacityPlane = spottedPlane.getCapacity();
                                                    List<Integer> seats = Util.createSeats(1, capacityPlane);
                                                    // System.out.println(seats);
                                                    List<FlightFare> flightFares = flightFareService.findAllFlightFares();
                                                    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                                                    TripBooking tripBooking = new TripBooking(currentDate, foundT.get().getId());
                                                    int idTB = this.tripBookingService.createTripBooking(tripBooking);
                                                    int optionTwo = Util.getIntInput("""

                                                    1. Añadir Pasajeros
                                                    2. Salir
                                                    """);
                                                    switch (optionTwo) {
                                                        case 1:
                                                            int numPassengers = Util.getIntInput(">> Ingrese el número de pasajeros que va a registrar: ");
                                                            for (int i = 1; i <= numPassengers; i++) {
                                                                Boolean isFound = true;
                                                                List<Integer> seatsOcuppied = tripBookingDetailService.findSeatNumbers(spottedTrip.getId());
                                                                // System.out.println(seatsOcuppied);
                                                                String name = Util.getStringInput(">> Ingrese el nombre del pasajero: ");
                                                                int age = Util.getIntInput(">> Ingrese la edad del pasajero: ");

                                                                List<DocumentType> documentTypes = this.customerService.findAllDocumentTypes();
                                                                documentTypes.forEach(document -> { System.out.println(document); }); 

                                                                int newDocumentTypeId;
                                                                do{
                                                                    newDocumentTypeId = Util.getIntInput(">> Ingrese el ID que corresponda al tipo de documento: ");
                                                                    idFound = this.customerService.getDocumentTypeId(newDocumentTypeId);
                                                                } while (idFound == -1);
                                                                
                                                                int docNumber = 0;
                                                                do {
                                                                    docNumber = Util.getIntInput(">> Introduzca el número de identificación del pasajero\n NOTA: debe ser un numero único"); 
                                                                }while (this.customerService.verifyDocumentNumber(docNumber) != 0);

                                                                for (int j = 0; j <= flightFares.size() - 1; j++) {
                                                                    System.out.println(flightFares.get(j).getId() + " - " + flightFares.get(j).getDescription() + " - " + flightFares.get(j).getDetails() + " - " + flightFares.get(j).getValue());
                                                                }
                                                                do {
                                                                    idFare = Util.getIntInput(">> Ingrese el ID de la tarifa:");
                                                                    idF = tripBookingService.getFlightFareId(idFare);
                                                                } while (idF == -1);
                                                                Customer  customer = new Customer(name, age, newDocumentTypeId, docNumber);
                                                                int idC = customerService.createCustomer(customer);
                                                                System.out.println(seats);
                                                                do {
                                                                    int seatNumber = Util.getIntInput(">> Ingrese el asiento: ");
                                                                    if (seatsOcuppied.contains(seatNumber)) {
                                                                        Util.showWarning("El asiento se encuentra ocupado");
                                                                    } else if (seats.contains(seatNumber)) {
                                                                        TripBookingDetail tripBookingDetail = new TripBookingDetail(idTB, idC, idFare, seatNumber);
                                                                        this.tripBookingDetailService.createTripBookingDetail(tripBookingDetail);
                                                                        isFound = false;
                                                                    } else {
                                                                        Util.showWarning("No existe el asiento");
                                                                    }
                                                                } while (isFound == true);
                                                            }
                                                            
                                                            int optionThree = Util.getIntInput("""
                                                            1. Realizar Pago
                                                            2. Salir
                                                            """);
                                                            switch (optionThree) {
                                                                case 1:
                                                                    List<PaymentForm> paymentForms = this.paymentFormService.findAllPaymentForms();
                                                                    for (PaymentForm paymentForm : paymentForms) {
                                                                        System.out.println(paymentForm.getId() + " - " + paymentForm.getDescription());
                                                                    }
                                                                    Optional<PaymentForm> foundP;
                                                                    do {
                                                                        int idPaymentForm = Util.getIntInput(">> Seleccione el ID de la forma de pago:");
                                                                        foundP = this.paymentFormService.findPaymentFormById(idPaymentForm);
                                                                        foundP.ifPresentOrElse(
                                                                            spottedPaymentForm -> {
                                                                                Payment payment = new Payment(idSpottedCustomer, spottedPaymentForm.getId(), idTB);
                                                                                this.paymentService.createPayment(payment);
                                                                                Util.showSuccess("Pago exitoso");
                                                                            },
                                                                            () -> {
                                                                                Util.showWarning("No se encontró la forma del pago");
                                                                            }
                                                                        );
                                                                    } while (foundP.isEmpty());
                                                                    
                                                                    break;
                                                                case 2:
                                                                    this.tripBookingService.deleteTripBooking(idTB);
                                                                    // DELETE CUSTOMER
                                                                    break;
                                                            }
                                                            break;
                                                        case 2:
                                                            this.tripBookingService.deleteTripBooking(idTB);
                                                            // DELETE CUSTOMER
                                                            break;
                                                    }
                                                }, 
                                                () -> {
                                                    Util.showWarning("No existe avión asociado a este vuelo");
                                                }
                                            );
                                        },
                                        ()-> {
                                            Util.showWarning("ID no encontrado o vuelo inexistente");
                                        }
                                    );
                                    break;
                            }
                        }
                    },
                    () -> {
                        Util.showWarning("No se encontró el cliente");
                    }
                );
                break;
        } 
    }
}