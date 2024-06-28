package com.agenciavuelos;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.Console.view.adapter.in.AdminConsoleAdapter;
import com.agenciavuelos.Console.view.adapter.in.ClientConsoleAdapter;
import com.agenciavuelos.Console.view.adapter.in.SalesAgentConsoleAdapter;
import com.agenciavuelos.Console.view.adapter.in.TechnicianConsoleAdapter;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/AGENCIA_VUELOS";
        String user = "campus2023";
        String password = "campus2023";
        
        Initializer initializer = new Initializer(url, user, password);
        AdminConsoleAdapter adminConsoleAdapter = new AdminConsoleAdapter(initializer);
        TechnicianConsoleAdapter technicianConsoleAdapter = new TechnicianConsoleAdapter(initializer);
        SalesAgentConsoleAdapter salesAgentConsoleAdapter = new SalesAgentConsoleAdapter(initializer);
        ClientConsoleAdapter clientConsoleAdapter = new ClientConsoleAdapter(initializer);


        String header = """
                                                _             _                         _           
        █████   ██████  ███████ ███    ██  ██████ ██  █████      
        ██   ██ ██       ██      ████   ██ ██      ██ ██   ██     
        ███████ ██   ███ █████   ██ ██  ██ ██      ██ ███████     
        ██   ██ ██    ██ ██      ██  ██ ██ ██      ██ ██   ██     
        ██   ██  ██████  ███████ ██   ████  ██████ ██ ██   ██     
                                                              
                                                              
                    ██████  ███████                                           
                    ██   ██ ██                                                
                    ██   ██ █████                                             
                    ██   ██ ██                                                
                    ██████  ███████                                           
                                                              
                                                              
            ██    ██ ██    ██ ███████ ██       ██████  ███████        
            ██    ██ ██    ██ ██      ██      ██    ██ ██             
            ██    ██ ██    ██ █████   ██      ██    ██ ███████        
            ██  ██   ██    ██ ██      ██      ██    ██      ██        
             ████     ██████  ███████ ███████  ██████  ███████  
    
        ----------------------------------------------------------
                    BIENVENIDO A NUESTRO PROGRAMA
        ----------------------------------------------------------                                                                  
        """;


        String[] mainOptions ={
            "▶ ( 1 ) Administrador",
            "▶ ( 2 ) Agente de ventas",
            "▶ ( 3 ) Tecnico en mantimiento",
            "▶ ( 4 ) Cliente",
            "▶ ( 5 ) Salir"

        };

        System.out.println(header);
        Util.printOptions(mainOptions);
        System.out.println(">> ¿Quien eres?. Digita la opcion.");
        int selectedOption = Util.rangeValidator(1, mainOptions.length);

        
        switch (selectedOption) {
            case 1: // admin
                adminConsoleAdapter.run();
                break;
        
            case 2: // agente de ventas
                salesAgentConsoleAdapter.run();
                break;
            
            case 3: // tecnico
                technicianConsoleAdapter.run();
                break;
            
            case 4: // cliente
                clientConsoleAdapter.run();
                break;
            
            default: // salir
                Util.showWarning("Gracias por preferirnos. Adios :)");
                break;
        }
        


    }

    

    
    
    
}
