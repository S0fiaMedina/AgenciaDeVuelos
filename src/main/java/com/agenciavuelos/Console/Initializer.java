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
import com.agenciavuelos.modules.customer.adapter.in.CustomerConsoleAdapter;
import com.agenciavuelos.modules.customer.adapter.out.CustomerMySQLRepository;
import com.agenciavuelos.modules.customer.application.CustomerService;
import com.agenciavuelos.modules.customer.infrastructure.CustomerRepository;
import com.agenciavuelos.modules.documentType.adapter.in.DocumentTypeConsoleAdapter;
import com.agenciavuelos.modules.documentType.adapter.out.DocumentTypeMySQLRepository;
import com.agenciavuelos.modules.documentType.application.DocumentTypeService;
import com.agenciavuelos.modules.documentType.infrastructure.DocumentTypeRepository;
import com.agenciavuelos.modules.employee.adapter.in.EmployeeConsoleAdapter;
import com.agenciavuelos.modules.employee.adapter.out.EmployeeMySQLRepository;
import com.agenciavuelos.modules.employee.application.EmployeeService;
import com.agenciavuelos.modules.employee.infrastructure.EmployeeRepository;
import com.agenciavuelos.modules.flightConnection.adapter.in.FlightConnectionConsoleAdapter;
import com.agenciavuelos.modules.flightConnection.adapter.out.FlightConnectionMySQLRepository;
import com.agenciavuelos.modules.flightConnection.application.FlightConnectionService;
import com.agenciavuelos.modules.flightConnection.infrastructure.FlightConnectionRepository;
import com.agenciavuelos.modules.flightFare.adapter.in.FlightFareConsoleAdapter;
import com.agenciavuelos.modules.flightFare.adapter.out.FlightFareMySQLRepository;
import com.agenciavuelos.modules.flightFare.application.FlightFareService;
import com.agenciavuelos.modules.flightFare.infrastructure.FlightFareRepository;
import com.agenciavuelos.modules.gate.adapter.in.GateConsoleAdapter;
import com.agenciavuelos.modules.gate.adapter.out.GateMySQLRepository;
import com.agenciavuelos.modules.gate.application.GateService;
import com.agenciavuelos.modules.gate.infrastructure.GateRepository;
import com.agenciavuelos.modules.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import com.agenciavuelos.modules.manufacturer.adapter.out.ManufacturerMySQLRepository;
import com.agenciavuelos.modules.manufacturer.application.ManufacturerService;
import com.agenciavuelos.modules.manufacturer.infrastructure.ManufacturerRepository;
import com.agenciavuelos.modules.model.adapter.in.ModelConsoleAdapter;
import com.agenciavuelos.modules.model.adapter.out.ModelMySQLRepository;
import com.agenciavuelos.modules.model.application.ModelService;
import com.agenciavuelos.modules.model.infrastructure.ModelRepository;
import com.agenciavuelos.modules.payment.adapter.out.PaymentMySQLRepository;
import com.agenciavuelos.modules.payment.infrastructure.PaymentRepository;
import com.agenciavuelos.modules.paymentForm.adapter.out.PaymentFormMySQLRepository;
import com.agenciavuelos.modules.paymentForm.infrastructure.PaymentFormRepository;
import com.agenciavuelos.modules.plane.adapter.in.PlaneConsoleAdapter;
import com.agenciavuelos.modules.plane.adapter.out.PlaneMySQLRepository;
import com.agenciavuelos.modules.plane.application.PlaneService;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;
import com.agenciavuelos.modules.revision.adapter.in.RevisionConsoleAdapter;
import com.agenciavuelos.modules.revision.adapter.out.RevisionMySQLRepository;
import com.agenciavuelos.modules.revision.application.RevisionService;
import com.agenciavuelos.modules.revision.infrastructure.RevisionRepository;
import com.agenciavuelos.modules.revisionDetail.adapter.out.RevisionDetailMySQLRepository;
import com.agenciavuelos.modules.revisionDetail.infrastructure.RevisionDetailRepository;
import com.agenciavuelos.modules.status.adapter.in.StatusConsoleAdapter;
import com.agenciavuelos.modules.status.adapter.out.StatusMySQLRepository;
import com.agenciavuelos.modules.status.application.StatusService;
import com.agenciavuelos.modules.status.infrastructure.StatusRepository;
import com.agenciavuelos.modules.trip.adapter.in.TripConsoleAdapterAdmin;
import com.agenciavuelos.modules.trip.adapter.out.TripMySQLRepository;
import com.agenciavuelos.modules.trip.application.TripService;
import com.agenciavuelos.modules.trip.infrastructure.TripRepository;
import com.agenciavuelos.modules.tripBooking.adapter.in.TripBookingConsoleAdapter;
import com.agenciavuelos.modules.tripBooking.adapter.in.TripBookingConsoleAdapterClient;
import com.agenciavuelos.modules.tripBooking.adapter.out.TripBookingMySQLRepository;
import com.agenciavuelos.modules.tripBooking.application.TripBookingService;
import com.agenciavuelos.modules.tripBooking.infrastructure.TripBookingRepository;
import com.agenciavuelos.modules.tripBookingDetail.adapter.out.TripBookingDetailMySQLRepository;
import com.agenciavuelos.modules.tripBookingDetail.infrastructure.TripBookingDetailRepository;
import com.agenciavuelos.modules.tripCrew.adapter.in.TripCrewConsoleAdapter;
import com.agenciavuelos.modules.tripCrew.adapter.out.TripCrewMySQLRepository;
import com.agenciavuelos.modules.tripCrew.application.TripCrewService;
import com.agenciavuelos.modules.tripCrew.infrastructure.TripCrewRepository;
import com.agenciavuelos.modules.tripulationRole.adapter.in.TripulationRoleConsoleAdapter;
import com.agenciavuelos.modules.tripulationRole.adapter.out.TripulationRoleMySQLRepository;
import com.agenciavuelos.modules.tripulationRole.application.TripulationRoleService;
import com.agenciavuelos.modules.tripulationRole.infrastructure.TripulationRoleRepository;


/**
 * Como lo dice su nombre, esta clase sirve para inicializar todos los repositorios necesarios,
 * de esta forma, se separan responsabilidades y se unifica la url, el usuario y la contraseña de mysql
 * en tres variables y no tener que hacerlo que colocarlas explicitamente cada vez que se inicialice algo
 * 
 * la verdad, me siento muy mal por esta clase xd
*/
public class Initializer {
    private String user;
    private String password;
    private String url;

    
    // location
    private final  CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AirportRepository airportRepository;
    private final GateRepository gateRepository;

    // aviones 
    private final ManufacturerRepository manufacturerRepository; 
    private final StatusRepository statusRepository;
    private final ModelRepository modelRepository;
    private final AirlineRepository airlineRepository;
    private final PlaneRepository planeRepository;
    private final RevisionRepository revisionRepository;
    
    // viajes 
    private final TripRepository tripRepository;
    private final FlightConnectionRepository flightConnectionRepository;
    private final TripCrewRepository tripCrewRepository;

    // reservas 
    private final TripBookingDetailRepository tripBookingDetailRepository;
    private final FlightFareRepository flightFareRepository;
    private final TripBookingRepository tripBookingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentFormRepository paymentFormRepository;

    // empleados  
    private final TripulationRoleRepository tripulationRoleRepository;
    private final EmployeeRepository employeeRepository;

    // clientes
    private final CustomerRepository customerRepository;
    private final DocumentTypeRepository documentTypeRepository;
    

    public Initializer(String url, String user, String password){
        this.user = user;
        this.password = password;
        this.url = url;

        // location
        this.countryRepository = new CountryMySQLRepository(url, user, password);
        this.cityRepository = new CityMySQLRepository(url, user, password);
        this.airportRepository = new AirportMySQLRepository(url, user, password);
        this.gateRepository = new GateMySQLRepository(url, user, password);
        

        // avion
        this.manufacturerRepository  = new ManufacturerMySQLRepository(url, user, password);
        this.statusRepository = new StatusMySQLRepository(url, user, password);
        this.modelRepository = new ModelMySQLRepository(url, user, password);
        this.airlineRepository = new AirlineMySQLRepository(url, user, password);
        this.planeRepository = new PlaneMySQLRepository(url, user, password);
        this.revisionRepository = new RevisionMySQLRepository(url, user, password);
        new RevisionDetailMySQLRepository(url, user, password);

        // reservas
        this.tripBookingDetailRepository = new TripBookingDetailMySQLRepository(url, user, password);
        this.flightFareRepository = new FlightFareMySQLRepository(url, user, password);
        this.tripBookingRepository = new TripBookingMySQLRepository(url, user, password);
        this.paymentRepository = new PaymentMySQLRepository(url, user, password);
        this.paymentFormRepository = new PaymentFormMySQLRepository(url, user, password);
        // viajes
        this.tripRepository = new TripMySQLRepository(url, user, password);
        this.flightConnectionRepository = new FlightConnectionMySQLRepository(url, user, password);
        this.tripCrewRepository = new TripCrewMySQLRepository(url, user, password);

        // empleados
        this.tripulationRoleRepository = new TripulationRoleMySQLRepository(url, user, password);
        this.employeeRepository = new EmployeeMySQLRepository(url, user, password);

        // clientes
        this.documentTypeRepository = new DocumentTypeMySQLRepository(url, user, password);
        this.customerRepository = new CustomerMySQLRepository(url, user, password);
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
        ManufacturerService manufacturerService = new ManufacturerService(manufacturerRepository);
        return new ManufacturerConsoleAdapter(manufacturerService);
    }


    // PAISES 
    public CountryConsoleAdapter startCountryConsoleAdapter(){
        CountryService countryService = new CountryService(countryRepository);
        return new CountryConsoleAdapter(countryService);
    }

    // CIUDADES
    public CityConsoleAdapter startCityConsoleAdapter(){
        CityService cityService = new CityService(cityRepository, countryRepository);
        return new CityConsoleAdapter(cityService);
    }

    // AEROPUERTOS
    public AirportConsoleAdapter startAirportConsoleAdapter() {
        AirportService airportService = new AirportService(airportRepository, cityRepository);
        return new AirportConsoleAdapter(airportService);
    }

    // AEROLINEAS

    public StatusConsoleAdapter startStatusConsoleAdapter(){
        StatusService statusService = new StatusService(statusRepository);
        return new StatusConsoleAdapter(statusService);
    }

    // modelos
    public ModelConsoleAdapter starModelConsoleAdapter(){
        ModelService modelService = new ModelService(modelRepository, manufacturerRepository);
        return new ModelConsoleAdapter(modelService);
    }

        // AEROLINEAS
    public AirlineConsoleAdapter startAirlineConsoleAdapter() {
        AirlineService airlineService = new AirlineService(airlineRepository);
        return new AirlineConsoleAdapter(airlineService);
    }

    // PUERTAS DE EMBARQUE
    public GateConsoleAdapter startGateConsoleAdapter() {
        GateService gateService = new GateService(gateRepository, airportRepository);
        return new GateConsoleAdapter(gateService);
    }

    // ROLES DE TRIPULACION
    public TripulationRoleConsoleAdapter startTripulationRoleConsoleAdapter() {
        TripulationRoleService tripulationRoleService = new TripulationRoleService(tripulationRoleRepository);
        return new TripulationRoleConsoleAdapter(tripulationRoleService);
    }

    // EMPLEADOS
    public EmployeeConsoleAdapter startEmployeeConsoleAdapter() {
        EmployeeService employeeService = new EmployeeService(employeeRepository, tripulationRoleRepository, airlineRepository, airportRepository);
        return new EmployeeConsoleAdapter(employeeService);
    }


    /**
     * RELACIONADOS A CLIENTES 
    */

    // TIPO DE DOCUMENTO 
    public DocumentTypeConsoleAdapter startDocumentTypeModule(){
        DocumentTypeService documentTypeService = new DocumentTypeService(documentTypeRepository);
        return new DocumentTypeConsoleAdapter(documentTypeService);
    }

    // CUSTOMER 
    public CustomerConsoleAdapter startCustomerModule(){
        CustomerService customerService = new CustomerService(customerRepository, documentTypeRepository);
        return new CustomerConsoleAdapter(customerService);
    }

    // AVIONES
    public PlaneConsoleAdapter startPlaneModule(){

        PlaneService planeService = new PlaneService(planeRepository, statusRepository, modelRepository, manufacturerRepository, airlineRepository);

        return new PlaneConsoleAdapter(planeService);
    }

    // REVISIONES
    public RevisionConsoleAdapter startRevisionModule(){
        RevisionDetailRepository revisionDetailRepository = new RevisionDetailMySQLRepository(url, user, password);
        RevisionService revisionService = new RevisionService(revisionRepository, planeRepository, employeeRepository, revisionDetailRepository);
        return new RevisionConsoleAdapter(revisionService);
    }

    // VIAJES (ADMIN)
    public TripConsoleAdapterAdmin startTripAdminModule(){
        TripService tripService = new TripService(tripRepository, airportRepository, flightConnectionRepository);
        return new TripConsoleAdapterAdmin(tripService);
    }

    // RESERVAS
    public TripBookingConsoleAdapter startTripBookingModule() {
        TripBookingService tripBookingService = new TripBookingService(tripRepository, planeRepository, tripBookingDetailRepository, tripBookingRepository, customerRepository, documentTypeRepository, flightFareRepository, paymentRepository, paymentFormRepository);
        return new TripBookingConsoleAdapter(tripBookingService);
    }

    // RESERVAS (CLIENNTE)
    public TripBookingConsoleAdapterClient startTripBookingClientModule() {
        TripBookingService tripBookingService = new TripBookingService(tripRepository, planeRepository, tripBookingDetailRepository, tripBookingRepository, customerRepository, documentTypeRepository, flightFareRepository, paymentRepository, paymentFormRepository);
        return new TripBookingConsoleAdapterClient(tripBookingService);
    }

    // TARIFAS
    public FlightFareConsoleAdapter startFlightFareModule() {
        FlightFareService flightFareService = new FlightFareService(flightFareRepository);
        return new FlightFareConsoleAdapter(flightFareService);
    }

    // CONEXIONES
    public FlightConnectionConsoleAdapter startFlightConnectionModule(){
        FlightConnectionService flightConnectionService = new FlightConnectionService(tripRepository, airportRepository, planeRepository, flightConnectionRepository);
        return new FlightConnectionConsoleAdapter(flightConnectionService);


    }

    // TRIPCREW
    public TripCrewConsoleAdapter startTripCrewConsoleAdapter() {
        TripRepository tripRepository = new TripMySQLRepository(url, user, password);
        TripCrewService tripCrewService = new TripCrewService(tripCrewRepository, employeeRepository, tripRepository);
        return new TripCrewConsoleAdapter(tripCrewService);
    }

    // UPDATE 

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }


    

    
}
