package com.agenciavuelos.modules.trip;

import com.agenciavuelos.Console.Initializer;

public class TestMain {

    public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/AGENCIA_VUELOS";
    String user = "root";
    String password = "root";
    Initializer initializer = new Initializer(url, user, password);
    
    initializer.startTripModule().run();

    }
    
}