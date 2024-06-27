package com.agenciavuelos.Console.view;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.airline.adapter.in.AirlineConsoleAdapter;
import com.agenciavuelos.modules.airport.adapter.in.AirportConsoleAdapter;
import com.agenciavuelos.modules.documentType.adapter.in.DocumentTypeConsoleAdapter;
import com.agenciavuelos.modules.employee.adapter.in.EmployeeConsoleAdapter;
import com.agenciavuelos.modules.flightFare.adapter.in.FlightFareConsoleAdapter;
import com.agenciavuelos.modules.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import com.agenciavuelos.modules.model.adapter.in.ModelConsoleAdapter;
import com.agenciavuelos.modules.plane.adapter.in.PlaneConsoleAdapter;

public class AdminView {

    private final Initializer initializer;
    private final ManufacturerConsoleAdapter manufacturerConsoleAdapter;
    private final ModelConsoleAdapter modelConsoleAdapter;
    private final AirlineConsoleAdapter airlineConsoleAdapter;
    private final EmployeeConsoleAdapter employeeConsoleAdapter;
    private final AirportConsoleAdapter airportConsoleAdapter;
    private final PlaneConsoleAdapter planeConsoleAdapter;
    private final FlightFareConsoleAdapter flightFareConsoleAdapter;
    private final DocumentTypeConsoleAdapter documentTypeConsoleAdapter;
    
    String[] gestionOptions = {
        "▶ (1) Gestión Fabricantes",
        "▶ (2) Gestión Modelos",
        "▶ (3) Gestión Aerolíneas",
        "▶ (4) Gestión Empleados",
        "▶ (5) Gestión Aeropuertos",
        "▶ (6) Gestión Aviones",
        "▶ (7) Gestión Tarifas de Vuelo",
        "▶ (8) Gestión Tipos de Documento",
        "▶ (9) Gestión Vuelos"
    };


    String header = """
    ##########################################
            BIENVENIDO, ADMINISTRADOR
    ##########################################                                                                  
    """;

    

    public AdminView(Initializer initializer) {
        this.initializer = initializer;
        this.manufacturerConsoleAdapter = initializer.startManufacturerModule();
        this.modelConsoleAdapter = initializer.starModelConsoleAdapter();
        this.airlineConsoleAdapter = initializer.startAirlineConsoleAdapter();
    }


    public void run(){
        System.out.println(header);
        Util.printOptions(gestionOptions);
        int selectedOption = Util.rangeValidator(1, this.gestionOptions.length);

        switch (selectedOption) {
            case 1: // fabricantes
                this.manufacturerConsoleAdapter.run();
                break;
        
            case 2: // modelos
                this.modelConsoleAdapter.run();
                break;
            
            case 3: // aerolineas
                this.
        }


    }

}
