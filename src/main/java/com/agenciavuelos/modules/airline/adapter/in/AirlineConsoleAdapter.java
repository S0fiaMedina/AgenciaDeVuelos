package com.agenciavuelos.modules.airline.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.airline.application.AirlineService;
import com.agenciavuelos.modules.airline.domain.Airline;

public class AirlineConsoleAdapter {
    private final AirlineService airlineService;

    // lista que contiene las opciones del menu
    private final  String[] airlineOptions = { 
        "1. Crear aerolinea",
        "2. Actualizar aerolinea",
        "3. Buscar aerolinea por ID",
        "4. Eliminar aerolinea",
        "5. Salir"
    };

    public AirlineConsoleAdapter(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE AEROLINEAS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.airlineOptions); 
        return Util.rangeValidator(1, airlineOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR
                // TODO: validacion de no repeticion de codigo de pais
                String name = Util.getStringInput(">> Ingrese el nombre de la aerolinea:");
                Airline  airline = new Airline(name);
                this.airlineService.createAirline(airline);
                break;
        
            case 2: // ACTUALIZAR

                List<Airline> airlines = this.airlineService.findAllAirlines();
                // Aqui se podria colocar una funcion para imprimir los manufactureros, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (airlines == null || airlines.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay aerolineas registradas");

                else{
                    int idAirline = Util.getIntInput(">> Introduzca el código a buscar: ");
                    Optional<Airline> optionalAirline = this.airlineService.findAirlineById(idAirline);

                
                    optionalAirline.ifPresentOrElse( // Aqui esta la funcion lambda

                        // ¿Es realmente necesario editar paises?
                        // XXX: revisar para poder cambiar el codigo del pais
                        updatedAirline -> {
                            System.out.println("Esta es la información actual de la aerolinea:\n " + updatedAirline);

                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre de la aerolinea: ");
                            
                            updatedAirline.setName(newName);

                            this.airlineService.updateAirline(updatedAirline);
                        },
                        
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                int SearchId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<Airline> foundAirline = this.airlineService.findAirlineById(SearchId);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundAirline.ifPresentOrElse(
                    spottedAirline -> { 
                    System.out.println("Esta es la información de la aerolinea encontrada:\n" + spottedAirline);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o aerolinea inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                int deleteId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<Airline> airlineToDelete = this.airlineService.findAirlineById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                airlineToDelete.ifPresentOrElse(
                    spottedAirline -> {
                        this.airlineService.deleteAirline(deleteId);
                        System.out.println("Aerolinea eliminada con éxito");
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o aerolinea inexistente");
                    });
        }
    }
}