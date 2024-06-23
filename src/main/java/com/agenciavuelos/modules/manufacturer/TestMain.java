package com.agenciavuelos.modules.manufacturer;

import com.agenciavuelos.modules.manufacturer.adapter.in.ManufacturerConsoleAdapter;

public class TestMain {

    public static void main(String[] args) {
        // No se si sea necesario realizar una clase de menus

    String url = "jdbc:mysql://localhost:3306/AGENCIA_VUELOS";
    String user = "root";
    String password = "queteimporta";
    Initializer initializer = new Initializer(url, user, password);

    // inicializados
    

    ManufacturerConsoleAdapter manufacturerConsoleAdapter = initializer.startManufacturerModule(); // se inicializan todas las cosas
    manufacturerConsoleAdapter.run();

    /**
     * INSERTS DE PRUEBA
    INSERT INTO manufacturer (name) VALUES ('Boeing');
    INSERT INTO manufacturer (name) VALUES ('Airbus');
    INSERT INTO manufacturer (name) VALUES ('Lockheed Martin');
    INSERT INTO manufacturer (name) VALUES ('Embraer');
    INSERT INTO manufacturer (name) VALUES ('Bombardier');
    */
    }
    
}
