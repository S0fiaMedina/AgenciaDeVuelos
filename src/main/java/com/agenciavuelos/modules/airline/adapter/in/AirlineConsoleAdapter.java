package com.agenciavuelos.modules.airline.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.airline.application.AirlineService;
import com.agenciavuelos.modules.airline.domain.Airline;

public class AirlineConsoleAdapter {
    private final AirlineService airlineService;

    private final  String[] airlineOptions = { 
        "1. Registrar Aerolinea",
        "2. Actualizar Aerolinea",
        "3. Consultar Aerolinea",
        "4. Eliminar Aerolinea",
        "5. Salir"
    };

    public AirlineConsoleAdapter(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    /**
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("GESTOR DE AEROLINEAS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.airlineOptions); 
        return Util.rangeValidator(1, airlineOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR
                
                String name = Util.getStringInput(">> Ingrese el nombre de la aerolinea:");
                Airline  airline = new Airline(name);
                this.airlineService.createAirline(airline);
                break;
        
            case 2: // ACTUALIZAR

                List<Airline> airlines = this.airlineService.findAllAirlines();

                if (airlines == null || airlines.isEmpty())
                    Util.showWarning("No hay aerolineas registradas");

                else{
                    int idAirline = Util.getIntInput(">> Introduzca el código a buscar: ");
                    Optional<Airline> optionalAirline = this.airlineService.findAirlineById(idAirline);

                    optionalAirline.ifPresentOrElse(
                        updatedAirline -> {
                            System.out.println("Esta es la información de la aerolinea encontrada:");
                            System.out.println(updatedAirline.toString());

                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre de la aerolinea: ");
                            
                            updatedAirline.setName(newName);

                            this.airlineService.updateAirline(updatedAirline);
                        },
                        
                        () -> {
                            Util.showWarning("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                int SearchId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<Airline> foundAirline = this.airlineService.findAirlineById(SearchId);
                
                foundAirline.ifPresentOrElse(
                    spottedAirline -> { 
                        System.out.println("Esta es la información de la aerolinea encontrada:");
                        System.out.println(spottedAirline.toString());
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o aerolinea inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR
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