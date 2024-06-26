package com.agenciavuelos.Console;

import com.agenciavuelos.modules.airline.adapter.in.AirlineConsoleAdapter;
import com.agenciavuelos.modules.airline.adapter.out.AirlineMySQLRepository;
import com.agenciavuelos.modules.airline.application.AirlineService;
import com.agenciavuelos.modules.airline.infrastructure.AirlineRepository;
import com.agenciavuelos.modules.airport.adapter.in.AirportConsoleAdapter;
import com.agenciavuelos.modules.airport.adapter.out.AirportMySQLRepository;
import com.agenciavuelos.modules.airport.application.AirportService;
import com.agenciavuelos.modules.airport.infrastructure.AirportRepository;
import com.agenciavuelos.modules.city.adapter.in.CityConsoleAdapter;
import com.agenciavuelos.modules.city.adapter.out.CityMySQLRepository;
import com.agenciavuelos.modules.city.application.CityService;
import com.agenciavuelos.modules.city.infrastructure.CityRepository;
import com.agenciavuelos.modules.country.adapter.in.CountryConsoleAdapter;
import com.agenciavuelos.modules.country.adapter.out.CountryMySQLRepository;
import com.agenciavuelos.modules.country.application.CountryService;
import com.agenciavuelos.modules.country.infrastructure.CountryRepository;
import com.agenciavuelos.modules.employee.adapter.in.EmployeeConsoleAdapter;
import com.agenciavuelos.modules.employee.adapter.out.EmployeeMySQLRepository;
import com.agenciavuelos.modules.employee.application.EmployeeService;
import com.agenciavuelos.modules.employee.infrastructure.EmployeeRepository;
import com.agenciavuelos.modules.flightFare.adapter.in.FlightFareConsoleAdapter;
import com.agenciavuelos.modules.flightFare.adapter.out.FlightFareMySQLRepository;
import com.agenciavuelos.modules.flightFare.application.FlightFareService;
import com.agenciavuelos.modules.flightFare.infrastructure.FlightFareRepository;
import com.agenciavuelos.modules.gate.adapter.in.GateConsoleAdapter;
import com.agenciavuelos.modules.gate.adapter.out.GateMySQLRepository;
import com.agenciavuelos.modules.gate.application.GateService;
import com.agenciavuelos.modules.gate.infrastructure.GateRepository;
import com.agenciavuelos.modules.customer.adapter.in.CustomerConsoleAdapter;
import com.agenciavuelos.modules.customer.adapter.out.CustomerMySQLRepository;
import com.agenciavuelos.modules.customer.application.CustomerService;
import com.agenciavuelos.modules.customer.infrastructure.CustomerRepository;
import com.agenciavuelos.modules.documentType.adapter.in.DocumentTypeConsoleAdapter;
import com.agenciavuelos.modules.documentType.adapter.out.DocumentTypeMySQLRepository;
import com.agenciavuelos.modules.documentType.application.DocumentTypeService;
import com.agenciavuelos.modules.documentType.infrastructure.DocumentTypeRepository;
import com.agenciavuelos.modules.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import com.agenciavuelos.modules.manufacturer.adapter.out.ManufacturerMySQLRepository;
import com.agenciavuelos.modules.manufacturer.application.ManufacturerService;
import com.agenciavuelos.modules.manufacturer.infrastructure.ManufacturerRepository;
import com.agenciavuelos.modules.trip.adapter.in.TripConsoleAdapter;
import com.agenciavuelos.modules.trip.adapter.out.TripMySQLRepository;
import com.agenciavuelos.modules.trip.application.TripService;
import com.agenciavuelos.modules.trip.infrastructure.TripRepository;
import com.agenciavuelos.modules.tripBooking.adapter.in.TripBookingConsoleAdapter;
import com.agenciavuelos.modules.tripBooking.adapter.out.TripBookingMySQLRepository;
import com.agenciavuelos.modules.tripBooking.application.TripBookingService;
import com.agenciavuelos.modules.tripBooking.infrastructure.TripBookingRepository;
import com.agenciavuelos.modules.tripulationRole.adapter.in.TripulationRoleConsoleAdapter;
import com.agenciavuelos.modules.tripulationRole.adapter.out.TripulationRoleMySQLRepository;
import com.agenciavuelos.modules.tripulationRole.application.TripulationRoleService;
import com.agenciavuelos.modules.tripulationRole.infrastructure.TripulationRoleRepository;


/**
 * Como lo dice su nombre, esta clase sirve para inicializar todos los repositorios necesarios,
 * de esta forma, se separan responsabilidades y se unifica la url, el usuario y la contraseña de mysql
 * en tres variables y no tener que hacerlo que colocarlas explicitamente cada vez que se inicialice algo
*/
public class Initializer {
    private String user;
    private String password;
    private String url;

    

    public Initializer(String url, String user, String password) {
        this.user = user;
        this.password = password;
        this.url = url;
    }



    /**
     * FUNCIONAMIENTO GENERAL:
     * Basicamente, cada funcion inicializa todo lo necesario para que su modulo funcione correctanente,
     * esto quiere decir que inicializa el repositorio, para lueg pasarlo al servicio e inicializarlo
     * y finalmente pasarselo al adaptador de consola y que finalmente el main o los menus solo tengan 
     * que perdirle los adaptadores a esta clase y de esta forma ahorrarle lineas de codigo y asi esa clase de main
     * solo tenga que conocer los adaptadores, nada más
     *
    */


    // FABRICANTES - MANUFACTURADORES
    public ManufacturerConsoleAdapter startManufacturerModule(){
        ManufacturerRepository manufacturerRepository = new ManufacturerMySQLRepository(url, user, password);
        ManufacturerService manufacturerService = new ManufacturerService(manufacturerRepository);
        return new ManufacturerConsoleAdapter(manufacturerService);
    }

    // PAISES 
    public CountryConsoleAdapter startCountryConsoleAdapter(){
        CountryRepository countryRepository = new CountryMySQLRepository(url, user, password);
        CountryService countryService = new CountryService(countryRepository);
        return new CountryConsoleAdapter(countryService);
    }

    // CIUDADES
    public CityConsoleAdapter startCityConsoleAdapter(){
        CountryRepository countryRepository = new CountryMySQLRepository(url, user, password);
        CountryService countryService = new CountryService(countryRepository);
        CityRepository cityRepository = new CityMySQLRepository(url, user, password);
        CityService cityService = new CityService(cityRepository, countryRepository);
        return new CityConsoleAdapter(cityService, countryService);
    }

    // AEROPUERTOS
    public AirportConsoleAdapter startAirportConsoleAdapter() {
        CountryRepository countryRepository = new CountryMySQLRepository(url, user, password);
        CityRepository cityRepository = new CityMySQLRepository(url, user, password);
        CityService cityService = new CityService(cityRepository, countryRepository);
        AirportRepository airportRepository = new AirportMySQLRepository(url, user, password);
        AirportService airportService = new AirportService(airportRepository, cityRepository);
        return new AirportConsoleAdapter(airportService, cityService);
    }

    // AEROLINEAS
    public AirlineConsoleAdapter startAirlineConsoleAdapter() {
        AirlineRepository airlineRepository = new AirlineMySQLRepository(url, user, password);
        AirlineService airlineService = new AirlineService(airlineRepository);
        return new AirlineConsoleAdapter(airlineService);
    }

    // PUERTAS DE EMBARQUE
    public GateConsoleAdapter startGateConsoleAdapter() {
        CityRepository cityRepository = new CityMySQLRepository(url, user, password);
        AirportRepository airportRepository = new AirportMySQLRepository(url, user, password);
        AirportService airportService = new AirportService(airportRepository, cityRepository);
        GateRepository gateRepository = new GateMySQLRepository(url, user, password);
        GateService gateService = new GateService(gateRepository, airportRepository);
        return new GateConsoleAdapter(gateService, airportService);
    }

    // ROLES DE TRIPULACION
    public TripulationRoleConsoleAdapter startTripulationRoleConsoleAdapter() {
        TripulationRoleRepository tripulationRoleRepository = new TripulationRoleMySQLRepository(url, user, password);
        TripulationRoleService tripulationRoleService = new TripulationRoleService(tripulationRoleRepository);
        return new TripulationRoleConsoleAdapter(tripulationRoleService);
    }

    // EMPLEADOS
    public EmployeeConsoleAdapter startEmployeeConsoleAdapter() {
        CityRepository cityRepository = new CityMySQLRepository(url, user, password);
        AirportRepository airportRepository = new AirportMySQLRepository(url, user, password);
        AirportService airportService = new AirportService(airportRepository, cityRepository);
        AirlineRepository airlineRepository = new AirlineMySQLRepository(url, user, password);
        AirlineService airlineService = new AirlineService(airlineRepository);
        TripulationRoleRepository tripulationRoleRepository = new TripulationRoleMySQLRepository(url, user, password);
        TripulationRoleService tripulationRoleService = new TripulationRoleService(tripulationRoleRepository);
        EmployeeRepository employeeRepository = new EmployeeMySQLRepository(url, user, password);
        EmployeeService employeeService = new EmployeeService(employeeRepository, tripulationRoleRepository, airlineRepository, airportRepository);
        return new EmployeeConsoleAdapter(employeeService, tripulationRoleService, airlineService, airportService);
    }


    /**
     * RELACIONADOS A CLIENTES 
    */

    // TIPO DE DOCUMENTO 
    public DocumentTypeConsoleAdapter startDocumentTypeModule(){
        DocumentTypeRepository documentTypeRepository = new DocumentTypeMySQLRepository(url, user, password);
        DocumentTypeService documentTypeService = new DocumentTypeService(documentTypeRepository);
        return new DocumentTypeConsoleAdapter(documentTypeService);
    }

    // CUSTOMER 
    public CustomerConsoleAdapter startCustomerModule(){
        DocumentTypeRepository documentTypeRepository = new DocumentTypeMySQLRepository(url, user, password); // re instancia repo de tipos de documentos (XXX: revisar en fututo)
        CustomerRepository customerRepository = new CustomerMySQLRepository(url, user, password);
        CustomerService customerService = new CustomerService(customerRepository, documentTypeRepository);
        return new CustomerConsoleAdapter(customerService);
    }

    // VUELOS
    public TripConsoleAdapter startTripModule() {
        AirportRepository airportRepository = new AirportMySQLRepository(url, user, password);
        TripRepository tripRepository = new TripMySQLRepository(url, user, password);
        TripService tripService = new TripService(tripRepository, airportRepository);
        return new TripConsoleAdapter(tripService);
    }

    public TripBookingConsoleAdapter startTripBookingModule() {
        AirportRepository airportRepository = new AirportMySQLRepository(url, user, password);
        TripRepository tripRepository = new TripMySQLRepository(url, user, password);
        TripService tripService = new TripService(tripRepository, airportRepository);
        TripBookingRepository tripBookingRepository = new TripBookingMySQLRepository(url, user, password);
        TripBookingService tripBookingService = new TripBookingService(tripBookingRepository, tripRepository);
        return new TripBookingConsoleAdapter(tripBookingService, tripService);
    }

    // TARIFAS
    public FlightFareConsoleAdapter startFlightFareModule() {
        FlightFareRepository flightFareRepository = new FlightFareMySQLRepository(url, user, password);
        FlightFareService flightFareService = new FlightFareService(flightFareRepository);
        return new FlightFareConsoleAdapter(flightFareService);
    }
}
