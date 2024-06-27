package com.agenciavuelos;

import com.agenciavuelos.Console.Util;

public class Main {
    public static void main(String[] args) {
        String[] mainOptions ={
            "( 1 ) Administrador",
            "( 2 ) Agente de ventas",
            "( 3 ) Tecnico en mantimiento",
            "( 4 ) Cliente"
    
    
    
        };
    
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

        Util.printOptions(mainOptions);
        int selectedOption = Util.rangeValidator(1, mainOptions.length);

        // manejar las opciones
        


    }

    

    
    
    
}
