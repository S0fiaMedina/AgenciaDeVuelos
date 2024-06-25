package com.agenciavuelos.modules.manufacturer;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.modules.model.adapter.in.ModelConsoleAdapter;
import com.agenciavuelos.modules.plane.adapter.in.PlaneConsoleAdapter;

public class TestMain {

    public static void main(String[] args) {
        // No se si sea necesario realizar una clase de menus

    String url = "jdbc:mysql://localhost:3306/AGENCIA_VUELOS";
    String user = "campus2023";
    String password = "campus2023";
    Initializer initializer = new Initializer(url, user, password);

    // inicializados
    

    PlaneConsoleAdapter planeConsoleAdapter = initializer.startPlaneModule();
    planeConsoleAdapter.run();

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
