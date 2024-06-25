package com.agenciavuelos.Console;

import com.agenciavuelos.modules.airline.adapter.in.AirlineConsoleAdapter;
import com.agenciavuelos.modules.airline.adapter.out.AirlineMySQLRepository;
import com.agenciavuelos.modules.airline.application.AirlineService;
import com.agenciavuelos.modules.airline.infrastructure.AirlineRepository;
import com.agenciavuelos.modules.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import com.agenciavuelos.modules.manufacturer.adapter.out.ManufacturerMySQLRepository;
import com.agenciavuelos.modules.manufacturer.application.ManufacturerService;
import com.agenciavuelos.modules.manufacturer.infrastructure.ManufacturerRepository;
import com.agenciavuelos.modules.model.adapter.in.ModelConsoleAdapter;
import com.agenciavuelos.modules.model.adapter.out.ModelMySQLRepository;
import com.agenciavuelos.modules.model.application.ModelService;
import com.agenciavuelos.modules.model.infrastructure.ModelRepository;
import com.agenciavuelos.modules.plane.adapter.in.PlaneConsoleAdapter;
import com.agenciavuelos.modules.plane.adapter.out.PlaneMySQLRepository;
import com.agenciavuelos.modules.plane.application.PlaneService;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;
import com.agenciavuelos.modules.status.adapter.in.StatusConsoleAdapter;
import com.agenciavuelos.modules.status.adapter.out.StatusMySQLRepository;
import com.agenciavuelos.modules.status.application.StatusService;
import com.agenciavuelos.modules.status.infrastructure.StatusRepository;


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

    // ESTADOS TODO: fusionar con aviones

    public StatusConsoleAdapter startStatusConsoleAdapter(){
        StatusRepository statusRepository = new StatusMySQLRepository(url, user, password);
        StatusService statusService = new StatusService(statusRepository);
        return new StatusConsoleAdapter(statusService);
    }

    // modelos
    public ModelConsoleAdapter starModelConsoleAdapter(){
        ManufacturerRepository manufacturerRepository = new ManufacturerMySQLRepository(url, user, password);
        ModelRepository modelRepository = new ModelMySQLRepository(url, user, password);
        ModelService modelService = new ModelService(modelRepository, manufacturerRepository);
        return new ModelConsoleAdapter(modelService);
    }

        // AEROLINEAS
    public AirlineConsoleAdapter startAirlineConsoleAdapter() {
        AirlineRepository airlineRepository = new AirlineMySQLRepository(url, user, password);
        AirlineService airlineService = new AirlineService(airlineRepository);
        return new AirlineConsoleAdapter(airlineService);
    }


    public PlaneConsoleAdapter startPlaneModule(){
        // incializacion de repositorios
        StatusRepository statusRepository = new StatusMySQLRepository(url, user, password);
        ManufacturerRepository manufacturerRepository = new ManufacturerMySQLRepository(url, user, password);
        ModelRepository modelRepository = new ModelMySQLRepository(url, user, password);
        AirlineRepository airlineRepository = new AirlineMySQLRepository(url, user, password);
        PlaneRepository planeRepository = new PlaneMySQLRepository(url, user, password);

        PlaneService planeService = new PlaneService(planeRepository, statusRepository, modelRepository, manufacturerRepository, airlineRepository);

        return new PlaneConsoleAdapter(planeService);


    }
}
