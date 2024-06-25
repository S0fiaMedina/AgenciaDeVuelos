package com.agenciavuelos.modules.employee;

import com.agenciavuelos.Console.Initializer;

public class TestMain {

    public static void main(String[] args) {
        // No se si sea necesario realizar una clase de menus

    String url = "jdbc:mysql://localhost:3306/AGENCIA_VUELOS";
    String user = "root";
    String password = "Germangie*738023";
    Initializer initializer = new Initializer(url, user, password);

    // inicializados
    
    initializer.startEmployeeConsoleAdapter().run();

    }
    
}