package com.agenciavuelos.modules.airport.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.airport.application.AirportService;
import com.agenciavuelos.modules.airport.domain.Airport;
import com.agenciavuelos.modules.city.application.CityService;
import com.agenciavuelos.modules.city.domain.City;
import com.agenciavuelos.modules.country.application.CountryService;
import com.agenciavuelos.modules.country.domain.Country;

public class AirportConsoleAdapter {
    private final AirportService airportService;
    private final CityService cityService;

    // lista que contiene las opciones del menu
    private final  String[] airportOptions = { 
        "1. Crear aeropuerto",
        "2. Actualizar aeropuerto",
        "3. Buscar aeropuerto por ID",
        "4. Eliminar aeropuerto",
        "5. Salir"
    };

    public AirportConsoleAdapter(AirportService airportService, CityService cityService) {
        this.airportService = airportService;
        this.cityService = cityService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE AEROPUERTOS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.airportOptions); 
        return Util.rangeValidator(1, airportOptions.length);
    }

    public void run(){
        String id;
        int idF;
        String idS = "";
        int optionSelected = getChoiceFromUser();
        List<City> cities = cityService.findAllCities();
        switch (optionSelected) {

            case 1: // CREAR
                // TODO: validacion de no repeticion de codigo de pais
                do {
                    id = Util.getStringInput(">> Ingrese el ID del aeropuerto: ");
                    idS = airportService.checkId(id);
                } while (idS != "");
                String name = Util.getStringInput(">> Ingrese el nombre del aeropuerto:");
                // System.out.println(cityService.findAllCountries().get(0).getId() + "" + cityService.findAllCountries().get(0).getName());
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
                // Aqui se podria colocar una funcion para imprimir los manufactureros, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (airports == null || airports.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay aeropuertos registrados");

                else{
                    String idAirport = Util.getStringInput(">> Introduzca el código a buscar: ");
                    Optional<Airport> optionalAirport = this.airportService.findAirportById(idAirport);

                
                    optionalAirport.ifPresentOrElse( // Aqui esta la funcion lambda

                        // ¿Es realmente necesario editar paises?
                        // XXX: revisar para poder cambiar el codigo del pais
                        updatedAirport -> {
                            System.out.println("Esta es la información actual del aeropuerto:\n " + updatedAirport);

                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre del aeropuerto: ");
                            
                            updatedAirport.setName(newName);

                            this.airportService.updateAirport(updatedAirport);
                        },
                        
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                String SearchId = Util.getStringInput(">> Introduzca el ID a buscar: ");
                Optional<Airport> foundAirport = this.airportService.findAirportById(SearchId);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundAirport.ifPresentOrElse(
                    spottedAirport -> { 
                    System.out.println("Esta es la información del aeropuerto encontrado:\n" + spottedAirport);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o aerppuerto inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                String deleteId = Util.getStringInput(">> Introduzca el ID a buscar: ");
                Optional<Airport> airportToDelete = this.airportService.findAirportById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                airportToDelete.ifPresentOrElse(
                    spottedAirport -> {
                        this.airportService.deteleAirport(deleteId);
                        System.out.println("Aeropuerto eliminado con éxito");
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o aeropuerto inexistente");
                    });
        }
    }
}