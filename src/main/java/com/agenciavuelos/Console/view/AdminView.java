package com.agenciavuelos.Console.view;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.Console.view.domain.Admin;


public class AdminView {
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
    ##########################################
            BIENVENIDO, ADMINISTRADOR
    ##########################################                                                                  
    """;

    

    public AdminView(Initializer initializer) {
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
            
            case 6: // aviones
                this.admin.getPlaneConsoleAdapter().run();
                break;
        }

    }

}
