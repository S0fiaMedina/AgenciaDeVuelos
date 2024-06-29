package com.agenciavuelos.Console.view.adapter.in;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.Console.view.domain.Client;

public class ClientConsoleAdapter {

    private final Client client;
    private final Initializer initializer;
    
    String[] clientOptions = {
        "▶ (1) Buscar Vuelos",
        "▶ (2) Consultar Reserva de Vuelo",
        "▶ (3) Modificar Reserva de Vuelo",
        "▶ (4) Cancelar Reserva de Vuelo"
    };

    String header = """

    █▀▀ █░░ █ █▀▀ █▄░█ ▀█▀ █▀▀
    █▄▄ █▄▄ █ ██▄ █░▀█ ░█░ ██▄
        """;

    public ClientConsoleAdapter(Initializer initializer){
        this.initializer = initializer;
        this.client = new Client(initializer);
    }

    public void run(){
        System.out.println(header);
        Util.printOptions(clientOptions);
        System.out.println(">> Digite la opcion de su preferencia: ");
        int optionSelected = Util.rangeValidator(1, clientOptions.length);

        switch (optionSelected) {
            case 1:
                // logica de buscar vuelos
                // seria algo como this.client.get<Adaptador que necesites>.run() o una funcion en especifico si es compartido por otro rol
                break;
        
            case 2:
                //consultar reserva
                break;
            case 3:
                // modificar reserva
                break;
            case 4:
                // cancelar reserva
                break;
                
            default: 
                break;

        }
    }
}
