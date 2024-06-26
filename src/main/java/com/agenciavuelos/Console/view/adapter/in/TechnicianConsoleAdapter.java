package com.agenciavuelos.Console.view.adapter.in;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.Console.view.domain.Technician;

public class TechnicianConsoleAdapter {
    private final Technician technician;


    String[] gestionOptions = {
        "▶ (1) Gestión de revisiones",
        "▶ (2) Salir"
    };

    String header = """
    ▀█▀ █▀▀ █▀▀ █▄░█ █ █▀▀ █▀█
    ░█░ ██▄ █▄▄ █░▀█ █ █▄▄ █▄█                                                                
    """;

    public TechnicianConsoleAdapter(Initializer initializer){
        this.technician = new Technician(initializer);
    }

    public void run(){
        boolean breaker = true;
        while(breaker){
            System.out.println(header);
            Util.printOptions(gestionOptions);
            System.out.println(">> Seleccione la opcion de su preferencia: ");
            int selectedOption = Util.rangeValidator(1, gestionOptions.length);

            switch (selectedOption) {
                case 1:
                    this.technician.getRevisionConsoleAdapter().run();
                    break;
            
                default:
                    System.out.println("Adios, tecnico :)");
                    breaker = false;
                    break;
            }
        
        }
    }
}
