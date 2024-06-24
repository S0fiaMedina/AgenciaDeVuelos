package com.agenciavuelos.Console;

import com.agenciavuelos.modules.airline.adapter.in.AirlineConsoleAdapter;
import com.agenciavuelos.modules.airline.adapter.out.AirlineMySQLRepository;
import com.agenciavuelos.modules.airline.application.AirlineService;
import com.agenciavuelos.modules.airline.infrastructure.AirlineRepository;
import com.agenciavuelos.modules.airport.adapter.in.AirportConsoleAdapter;
import com.agenciavuelos.modules.airport.adapter.out.AirportMySQLRepository;
import com.agenciavuelos.modules.airport.application.AirportService;
import com.agenciavuelos.modules.airport.domain.Airport;
import com.agenciavuelos.modules.airport.infrastructure.AirportRepository;
import com.agenciavuelos.modules.city.adapter.in.CityConsoleAdapter;
import com.agenciavuelos.modules.city.adapter.out.CityMySQLRepository;
import com.agenciavuelos.modules.city.application.CityService;
import com.agenciavuelos.modules.city.infrastructure.CityRepository;
import com.agenciavuelos.modules.country.adapter.in.CountryConsoleAdapter;
import com.agenciavuelos.modules.country.adapter.out.CountryMySQLRepository;
import com.agenciavuelos.modules.country.application.CountryService;
import com.agenciavuelos.modules.country.infrastructure.CountryRepository;
import com.agenciavuelos.modules.city.adapter.in.CityConsoleAdapter;
import com.agenciavuelos.modules.city.adapter.out.CityMySQLRepository;
import com.agenciavuelos.modules.city.application.CityService;
import com.agenciavuelos.modules.city.infrastructure.CityRepository;
import com.agenciavuelos.modules.country.adapter.in.CountryConsoleAdapter;
import com.agenciavuelos.modules.country.adapter.out.CountryMySQLRepository;
import com.agenciavuelos.modules.country.application.CountryService;
import com.agenciavuelos.modules.country.infrastructure.CountryRepository;
import com.agenciavuelos.modules.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import com.agenciavuelos.modules.manufacturer.adapter.out.ManufacturerMySQLRepository;
import com.agenciavuelos.modules.manufacturer.application.ManufacturerService;
import com.agenciavuelos.modules.manufacturer.infrastructure.ManufacturerRepository;


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
    /* public CountryConsoleAdapter startCountryConsoleAdapter(){
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

    public AirportConsoleAdapter startAirportConsoleAdapter() {
        CountryRepository countryRepository = new CountryMySQLRepository(url, user, password);
        CityRepository cityRepository = new CityMySQLRepository(url, user, password);
        CityService cityService = new CityService(cityRepository, countryRepository);
        AirportRepository airportRepository = new AirportMySQLRepository(url, user, password);
        AirportService airportService = new AirportService(airportRepository, cityRepository);
        return new AirportConsoleAdapter(airportService, cityService);
    } */

    public AirlineConsoleAdapter startAirlineConsoleAdapter() {
        AirlineRepository airlineRepository = new AirlineMySQLRepository(url, user, password);
        AirlineService airlineService = new AirlineService(airlineRepository);
        return new AirlineConsoleAdapter(airlineService);
    }
}
