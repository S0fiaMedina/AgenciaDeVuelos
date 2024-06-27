package com.agenciavuelos.Console.view;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.manufacturer.adapter.in.ManufacturerConsoleAdapter;

public class AdminView {

    private final Initializer initializer;
    private final ManufacturerConsoleAdapter manufacturerConsoleAdapter;
    
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
    }


    public void run(){
        System.out.println(header);
        Util.printOptions(gestionOptions);
        int selectedOption = Util.rangeValidator(1, this.gestionOptions.length);

        switch (selectedOption) {
            case 1: // crud manufacturadores
                this.manufacturerConsoleAdapter.run();
                break;
        
            default:
                break;
        }


    }

}
