package com.agenciavuelos.modules.trip.adapter.in;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.customer.application.CustomerService;
import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.documentType.domain.DocumentType;
import com.agenciavuelos.modules.flightFare.application.FlightFareService;
import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.trip.application.TripService;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.tripBooking.application.TripBookingService;
import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.tripBookingDetail.application.TripBookingDetailService;
import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;




public class TripConsoleAdapter {
    private final TripBookingService tripBookingService;
    private final TripBookingDetailService tripBookingDetailService;
    private final CustomerService customerService;
    private final FlightFareService flightFareService;
    private final TripService tripService;

    private final  String[] tripOptions = { 
        "1. Registrar Vuelo",
        "2. Actualizar Vuelo",
        "3. Consultar Vuelo",
        "4. Eliminar Vuelo",
        "5. Buscar Vuelo",
        "6. Salir"
    };

	public TripConsoleAdapter(TripService tripService, TripBookingService tripBookingService,
			TripBookingDetailService tripBookingDetailService, CustomerService customerService,
			FlightFareService flightFareService) {
		this.tripService = tripService;
		this.tripBookingService = tripBookingService;
		this.tripBookingDetailService = tripBookingDetailService;
		this.customerService = customerService;
		this.flightFareService = flightFareService;
	}

	/**
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

                if (trips == null || trips.isEmpty())
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
                break;
            
            case 3: // BUSCAR POR ID
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
                break;
            
            case 4: // ELIMINAR
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
                    break;
            case 5:
                Boolean iCorrect = true;
                String dateD;
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
                    }
                    int optionOne = Util.getIntInput("""

                    1. Seleccionar Vuelo
                    2. Salir
                    """);
                    switch (optionOne) {
                        case 1:
                            int choice = Util.getIntInput(">> Ingrese el código del vuelo que desea seleccionar: ");
                            Optional<Trip> foundT = this.tripService.findTripById(choice);
                            
                            foundT.ifPresentOrElse(
                                spottedTrip -> {
                                    // SE DEBE MOSTRAR LOS DATOS DE ESCALAS
                                    System.out.println("Esta es la información del vuelo seleccionado:\n" + spottedTrip.getDate() + " - " + spottedTrip.getNameCityD() + " - " + spottedTrip.getNameCityA() + " - " + spottedTrip.getPrice());
                                    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                                    TripBooking tripBooking = new TripBooking(currentDate, foundT.get().getId());
                                    int idTB = this.tripBookingService.createTripBooking(tripBooking);

                                    int idFound;
                                    int idF;
                                    int idFare;
                                    List<FlightFare> flightFares = flightFareService.findAllFlightFares();
                                    int optionTwo = Util.getIntInput("""

                                    1. Añadir Pasajeros
                                    2. Salir
                                    """);
                                    switch (optionTwo) {
                                        case 1:
                                            int numPassengers = Util.getIntInput(">> Ingrese el número de pasajeros que va a registrar: ");
                                            for (int i = 1; i <= numPassengers; i++) {
                                                String name = Util.getStringInput(">> Ingrese el nombre del pasajero: ");
                                                int age = Util.getIntInput(">> Ingrese la edad del pasajero: ");

                                                List<DocumentType>  documentTypes = this.customerService.findAllDocumentTypes();
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
                                                TripBookingDetail tripBookingDetail = new TripBookingDetail(idTB, idC, idFare);
                                                this.tripBookingDetailService.createTripBookingDetail(tripBookingDetail);
                                            }
                                            break;
                                        case 2:
                                            this.tripBookingService.deleteTripBooking(idTB);
                                            break;
                                    }
                                },
                                ()-> {
                                    Util.showWarning("ID no encontrado o vuelo inexistente");
                                }
                            );
                            
                            break;
                    }
                }
                break;
        } 
    }
}