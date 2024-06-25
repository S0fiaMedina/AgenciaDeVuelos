package com.agenciavuelos.modules.flightFare.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.flightFare.application.FlightFareService;

public class FlightFareConsoleAdapter {
    private final FlightFareService flightFareService;

    // lista que contiene las opciones del menu
    private final  String[] flightFareOptions = { 
        "1. Crear tarifa",
        "2. Actualizar tarifa",
        "3. Buscar tarifa por ID",
        "4. Eliminar tarifa",
        "5. Salir"
    };

    public FlightFareConsoleAdapter(FlightFareService flightFareService) {
        this.flightFareService = flightFareService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE TARIFAS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.flightFareOptions); 
        return Util.rangeValidator(1, flightFareOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR
                // TODO: validacion de no repeticion de codigo de pais
                String description = Util.getStringInput(">> Ingrese la descripción de la tarifa:");
                String details = Util.getStringInput(">> Ingrese los detalles:");
                Double value = Util.getDoubleInput(">> Ingrese el valor de la tarifa:");
                FlightFare flightFare = new FlightFare(description, details, value);
                this.flightFareService.createFlightFare(flightFare);
                break;
        
            case 2: // ACTUALIZAR

                List<FlightFare> flightFares = this.flightFareService.findAllFlightFares();
                // Aqui se podria colocar una funcion para imprimir los manufactureros, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (flightFares == null || flightFares.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay tarifas registrados");

                else{
                    int idFlightFare = Util.getIntInput(">> Introduzca el código a buscar: ");
                    Optional<FlightFare> optionalFlightFare = this.flightFareService.findFlightFareById(idFlightFare);

                
                    optionalFlightFare.ifPresentOrElse( // Aqui esta la funcion lambda

                        // ¿Es realmente necesario editar paises?
                        // XXX: revisar para poder cambiar el codigo del pais
                        updatedFlightFare -> {
                            System.out.println("Esta es la información actual de la tarifa:\n " + updatedFlightFare);

                            String newDescription = Util.getStringInput(">> Ingrese la descripción de la tarifa:");
                            String newDetails = Util.getStringInput(">> Ingrese los detalles:");
                            Double newValue = Util.getDoubleInput(">> Ingrese el valor de la tarifa:");
                            
                            updatedFlightFare.setDescription(newDescription);
                            updatedFlightFare.setDetails(newDetails);
                            updatedFlightFare.setValue(newValue);
                            this.flightFareService.updateFlightFare(updatedFlightFare);
                        },
                        
                        () -> {
                            Util.showWarning("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                int SearchId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<FlightFare> foundFlightFare = this.flightFareService.findFlightFareById(SearchId);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundFlightFare.ifPresentOrElse(
                    spottedFlightFare -> { 
                        System.out.println("Esta es la información de la tarifa encontrado:\n" + spottedFlightFare);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o tarifa inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                int deleteId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<FlightFare> flightFareToDelete = this.flightFareService.findFlightFareById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                flightFareToDelete.ifPresentOrElse(
                    spottedFlightFare -> {
                        this.flightFareService.deleteFlightFare(deleteId);
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o tarifa inexistente");
                    });
        }
    }
}