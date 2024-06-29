package com.agenciavuelos.modules.tripBooking.adapter.in;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.documentType.domain.DocumentType;
import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.payment.domain.Payment;
import com.agenciavuelos.modules.paymentForm.domain.PaymentForm;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.tripBooking.application.TripBookingService;
import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;


public class TripBookingConsoleAdapterClient {
    private final TripBookingService tripBookingService;

    private final  String[] tripOptions = { 
        "1. Buscar Vuelo",
        "2. Consultar Reserva de Vuelo",
        "3. Modificar Reserva de Vuelo",
        "4. Cancelar Reserva de Vuelo",
        "5. Salir"
    };

    public TripBookingConsoleAdapterClient(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    /**
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("GESTIÓN DE RESERVAS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.tripOptions); 
        return Util.rangeValidator(1, tripOptions.length);
    }

    public void run(){
        // boolean isCorrect = true;
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {
                
            case 1:
                this.searchTrip();
                break;
            case 2:
                this.searchTripBooking();
                break;
            case 3:
                this.updateTripBooking();
                break;
            case 4:
                this.cancelTripBooking();
                break;
        } 
    }

    public void searchTrip() {
        int documentNumber = Util.getIntInput(">> Ingrese su número de documento:");
        Optional<Customer> foundC = this.tripBookingService.findByDocumentNumber(documentNumber);
                
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

                List<Trip> foundTripsList = this.tripBookingService.searchTrips(nameCityD, nameCityA, dateD);
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
                            
                            Optional<Trip> foundT = this.tripBookingService.findTripById(choice);
                            
                            foundT.ifPresentOrElse(
                                spottedTrip -> {
                                    // SE DEBE MOSTRAR LOS DATOS DE ESCALAS
                                    System.out.println("Esta es la información del vuelo seleccionado:\n" + spottedTrip.getDate() + " - " + spottedTrip.getNameCityD() + " - " + spottedTrip.getNameCityA() + " - " + spottedTrip.getPrice());

                                    Optional<Plane> tripPlane = this.tripBookingService.findByTrip(foundT.get().getId());
                                    tripPlane.ifPresentOrElse(
                                        spottedPlane -> {
                                            int idF;
                                            int idFare;
                                            int idFound;
                                            int capacityPlane = spottedPlane.getCapacity();
                                            List<Integer> seats = Util.createSeats(1, capacityPlane);
                                            // System.out.println(seats);
                                            List<FlightFare> flightFares = tripBookingService.findAllFlightFares();
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
                                                        List<Integer> seatsOcuppied = tripBookingService.findSeatNumbers(spottedTrip.getId());
                                                        // System.out.println(seatsOcuppied);
                                                        String name = Util.getStringInput(">> Ingrese el nombre del pasajero: ");
                                                        int age = Util.getIntInput(">> Ingrese la edad del pasajero: ");

                                                        List<DocumentType> documentTypes = this.tripBookingService.findAllDocumentTypes();
                                                        documentTypes.forEach(document -> { System.out.println(document); }); 

                                                        int newDocumentTypeId;
                                                        do{
                                                            newDocumentTypeId = Util.getIntInput(">> Ingrese el ID que corresponda al tipo de documento: ");
                                                            idFound = this.tripBookingService.getDocumentTypeId(newDocumentTypeId);
                                                        } while (idFound == -1);
                                                        
                                                        int docNumber = 0;
                                                        do {
                                                            docNumber = Util.getIntInput(">> Introduzca el número de identificación del pasajero\n NOTA: debe ser un numero único"); 
                                                        }while (this.tripBookingService.verifyDocumentNumber(docNumber) != 0);

                                                        for (int j = 0; j <= flightFares.size() - 1; j++) {
                                                            System.out.println(flightFares.get(j).getId() + " - " + flightFares.get(j).getDescription() + " - " + flightFares.get(j).getDetails() + " - " + flightFares.get(j).getValue());
                                                        }
                                                        do {
                                                            idFare = Util.getIntInput(">> Ingrese el ID de la tarifa:");
                                                            idF = tripBookingService.getFlightFareId(idFare);
                                                        } while (idF == -1);
                                                        Customer  customer = new Customer(name, age, newDocumentTypeId, docNumber);
                                                        int idC = tripBookingService.createCustomer(customer);
                                                        System.out.println(seats);
                                                        do {
                                                            int seatNumber = Util.getIntInput(">> Ingrese el asiento: ");
                                                            if (seatsOcuppied.contains(seatNumber)) {
                                                                Util.showWarning("El asiento se encuentra ocupado");
                                                            } else if (seats.contains(seatNumber)) {
                                                                TripBookingDetail tripBookingDetail = new TripBookingDetail(idTB, idC, idFare, seatNumber);
                                                                this.tripBookingService.createTripBookingDetail(tripBookingDetail);
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
                                                            List<PaymentForm> paymentForms = this.tripBookingService.findAllPaymentForms();
                                                            for (PaymentForm paymentForm : paymentForms) {
                                                                System.out.println(paymentForm.getId() + " - " + paymentForm.getDescription());
                                                            }
                                                            Optional<PaymentForm> foundP;
                                                            do {
                                                                int idPaymentForm = Util.getIntInput(">> Seleccione el ID de la forma de pago:");
                                                                foundP = this.tripBookingService.findPaymentFormById(idPaymentForm);
                                                                foundP.ifPresentOrElse(
                                                                    spottedPaymentForm -> {
                                                                        Payment payment = new Payment(idSpottedCustomer, spottedPaymentForm.getId(), idTB);
                                                                        this.tripBookingService.createPayment(payment);
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
    }

    public void searchTripBooking() {
        int docuNumber = Util.getIntInput(">> Ingrese su número de documento:");
        Optional<Customer> foundCust = this.tripBookingService.findByDocumentNumber(docuNumber);
        foundCust.ifPresentOrElse(
            spottedCustomer -> {
                List<Integer> bookingsList = this.tripBookingService.findBookingsByCustomerId(spottedCustomer.getId());
                int searchID = Util.getIntInput(">> Introduzca el ID de la reserva: ");
                if (bookingsList.contains(searchID)) {
                    List<TripBooking> tripBookingList = this.tripBookingService.findBookingById(searchID);
                    if (tripBookingList == null || tripBookingList.isEmpty()) {
                        Util.showWarning("No se encontraron reservas");
                    } else {
                        System.out.println("RESERVA ENCONTRADA");
                        tripBookingList.forEach(booking -> {System.out.println(booking);});
                    }
                } else {
                    Util.showWarning("La reserva solicitada no pertenece al cliente");
                }
            },
            () -> {
                Util.showWarning("No se encontró al cliente");
            }
        );
    }

    public void updateTripBooking(){
        String[] updateOptions = {
            "1. Editar fecha."
        };

        // se obtiene id del cliente 
        int updateCustomerId = Util.getIntInput(">> Ingrese el id del cliente: ");
        int updateBookingId  = Util.getIntInput(">> Ingrese el id de la reserva: ");

        Optional<TripBooking> foundBooking = this.tripBookingService.findTripBookingOfCustomer(updateCustomerId, updateBookingId);
        foundBooking.ifPresentOrElse(
            spottedBooking -> {

            }, 
            () -> {
                Util.showWarning("Reserva no encontrada o inexistente");
            });
    }

    public void cancelTripBooking() {
        int docNumber = Util.getIntInput(">> Ingrese su número de documento:");
        Optional<Customer> foundCustomer = this.tripBookingService.findByDocumentNumber(docNumber);
        foundCustomer.ifPresentOrElse(
            spottedCustomer -> {
                List<Integer> bookingsList = this.tripBookingService.findBookingsByCustomerId(spottedCustomer.getId());
                int searchID = Util.getIntInput(">> Introduzca el ID de la reserva: ");
                if (bookingsList.contains(searchID)) {
                    List<TripBooking> tripBookingList = this.tripBookingService.findBookingById(searchID);
                    if (tripBookingList == null || tripBookingList.isEmpty()) {
                        Util.showWarning("No se encontraron reservas");
                    } else {
                        String conf = Util.getStringInput("Confirme la cancelación con 'S', presione cualquier otra letra para salir");
                        if (conf.equals("S")) {
                            this.tripBookingService.deleteTripBooking(searchID);
                        }
                    }
                } else {
                    Util.showWarning("La reserva solicitada no pertenece al cliente");
                }
            },
            () -> {
                Util.showWarning("No se encontró al cliente");
            }
        );
    }
}
