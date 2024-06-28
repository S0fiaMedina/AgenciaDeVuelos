package com.agenciavuelos.Console.view.adapter.in;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.Console.view.domain.Admin;


public class AdminConsoleAdapter {
    private final Admin admin;

    
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

    ▄▀█ █▀▄ █▀▄▀█ █ █▄░█ █ █▀ ▀█▀ █▀█ ▄▀█ █▀▄ █▀█ █▀█
    █▀█ █▄▀ █░▀░█ █ █░▀█ █ ▄█ ░█░ █▀▄ █▀█ █▄▀ █▄█ █▀▄                                                               
    """;

    

    public AdminConsoleAdapter(Initializer initializer) {
        this.admin = new Admin(initializer);
    }


    public void run(){
        System.out.println(header);
        Util.printOptions(gestionOptions);
        System.out.println(">> Seleccione la opcion de su preferencia");
        int selectedOption = Util.rangeValidator(1, this.gestionOptions.length);


        switch (selectedOption) {
            case 1: // fabricantes
                this.admin.getManufacturerConsoleAdapter().run();
                break;
        
            case 2: // modelos
                this.admin.getModelConsoleAdapter().run();
                break;

            case 3: // empleados
                this.admin.getAirlineConsoleAdapter().run();
                break;
            case 4:
                this.employeeHandler();
                break;
                
            case 5:
                this.admin.getAirportConsoleAdapter().run();
                break;

            case 6: // aviones
                this.admin.getPlaneConsoleAdapter().run();
                break;
            case 7: // tarifas de vuelo
                this.admin.getFlightFareConsoleAdapter().run();
                break;

            case 8: // tipos de documento
                this.admin.getDocumentTypeConsoleAdapter().run();
                break;
        }

    }

    public void employeeHandler(){
        String[] employeeOptions = {
            "1 -> Registrar empleado. ",
            "2.-> Gestionar tripulaciones de vuelo. ",
            "3 -> Salir."
        };
        
        Util.printOptions(employeeOptions);
        System.out.println(">> Escoge la opcion de tu preferencia.");
        int optionSelected = Util.rangeValidator(1, employeeOptions.length);

        switch (optionSelected) {
            case 1:
                this.admin.getEmployeeConsoleAdapter().run();
                break;
        
            case 2:
                this.admin.getTripCrewConsoleAdapter().run();
                break;

            default :
                break;
        }
    }

}
