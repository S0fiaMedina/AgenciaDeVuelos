package com.agenciavuelos.modules.airport.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.airport.application.AirportService;
import com.agenciavuelos.modules.airport.domain.Airport;
import com.agenciavuelos.modules.city.domain.City;

public class AirportConsoleAdapter {
    private final AirportService airportService;

    private final  String[] airportOptions = { 
        "1. Registrar Aeropuerto",
        "2. Actualizar Aeropuerto",
        "3. Consultar Aeropuerto",
        "4. Eliminar Aeropuerto",
        "5. Salir"
    };

    public AirportConsoleAdapter(AirportService airportService) {
        this.airportService = airportService;
    }

    /**
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE AEROPUERTOS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.airportOptions); 
        System.out.println(">> Escoja la opcion de su preferencia");
        return Util.rangeValidator(1, airportOptions.length);
    }

    public void run(){
        String id;
        int idF;
        String idS = "";
        int optionSelected = getChoiceFromUser();
        List<City> cities = airportService.findAllCities();
        switch (optionSelected) {

            case 1: // CREAR
                do {
                    id = Util.getStringInput(">> Ingrese el ID del aeropuerto: ");
                    idS = airportService.checkId(id);
                } while (idS != "");

                System.out.println("------------> CIUDADES DISPONIBLES <-------------");
                String name = Util.getStringInput(">> Ingrese el nombre del aeropuerto:");
                for (int i = 0; i <= cities.size() - 1; i++) {
                    System.out.println(cities.get(i).getId() + " - " + cities.get(i).getName());
                }
                do {
                    int idCity = Util.getIntInput(">> Ingrese el ID de la ciudad al que pertenece el aeropuerto:");
                    idF = airportService.getCityId(idCity);
                } while (idF == -1);
                Airport airport = new Airport(id, name, idF);
                this.airportService.createAirport(airport);
                break;
        
            case 2: // ACTUALIZAR

                List<Airport> airports = this.airportService.findAllAirports();

                if (airports == null || airports.isEmpty()  )
                    Util.showWarning("No hay aeropuertos registrados");

                else{
                    String idAirport = Util.getStringInput(">> Introduzca el código a buscar: ");
                    Optional<Airport> optionalAirport = this.airportService.findAirportById(idAirport);

                
                    optionalAirport.ifPresentOrElse(
                        updatedAirport -> {
                            System.out.println("Esta es la información actual del aeropuerto:\n " + updatedAirport.getId() + " - " + updatedAirport.getName());

                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre del aeropuerto: ");
                            
                            updatedAirport.setName(newName);

                            this.airportService.updateAirport(updatedAirport);
                        },
                        
                        () -> {
                            Util.showWarning("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                String SearchId = Util.getStringInput(">> Introduzca el ID a buscar: ");
                Optional<Airport> foundAirport = this.airportService.findAirportById(SearchId);
                
                foundAirport.ifPresentOrElse(
                    spottedAirport -> { 
                    System.out.println("Esta es la información del aeropuerto encontrado:\n" + spottedAirport.getId() + " - " + spottedAirport.getName());
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o aeropuerto inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR
                String deleteId = Util.getStringInput(">> Introduzca el ID a eliminar: ");
                Optional<Airport> airportToDelete = this.airportService.findAirportById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                airportToDelete.ifPresentOrElse(
                    spottedAirport -> {
                        this.airportService.deleteAirport(deleteId);
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o aeropuerto inexistente");
                    });
        }
    }
}