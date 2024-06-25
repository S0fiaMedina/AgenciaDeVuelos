package com.agenciavuelos.modules.gate.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.gate.application.GateService;
import com.agenciavuelos.modules.gate.domain.Gate;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.airport.application.AirportService;
import com.agenciavuelos.modules.airport.domain.Airport;

public class GateConsoleAdapter {
    private final GateService gateService;
    private final AirportService airportService;

    // lista que contiene las opciones del menu
    private final  String[] gateOptions = { 
        "1. Crear puerta de embarque",
        "2. Actualizar puerta de embarque",
        "3. Buscar puerta de embarque por ID",
        "4. Eliminar puerta de embarque",
        "5. Salir"
    };

    public GateConsoleAdapter(GateService gateService, AirportService airportService) {
        this.gateService = gateService;
        this.airportService = airportService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE PUERTAS DE EMBARQUE");
        System.out.println("-------------------------------------");
        Util.printOptions(this.gateOptions); 
        return Util.rangeValidator(1, gateOptions.length);
    }

    public void run(){
        String idF;
        int optionSelected = getChoiceFromUser();
        List<Airport> airports = airportService.findAllAirports();
        switch (optionSelected) {

            case 1: // CREAR
                // TODO: validacion de no repeticion de codigo de pais
                String number = Util.getStringInput(">> Ingrese el número de la puerta de embarque:");
                // System.out.println(airportService.findAllAirports().get(0).getId() + "" + airportService.findAllAirports().get(0).getName());
                for (int i = 0; i <= airports.size() - 1; i++) {
                    System.out.println(airports.get(i).getId() + " - " + airports.get(i).getName());
                }
                do {
                    String idAirport = Util.getStringInput(">> Ingrese el ID del aeropuerto al que pertenece la puerta de embarque:");
                    idF = gateService.getAirportId(idAirport);
                } while (idF == "");
                Gate  gate = new Gate(number, idF);
                this.gateService.createGate(gate);
                break;
        
            case 2: // ACTUALIZAR

                List<Gate> cities = this.gateService.findAllGates();
                // Aqui se podria colocar una funcion para imprimir los manufactureros, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (cities == null || cities.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay puertas de embarque registradas");

                else{
                    int idGate = Util.getIntInput(">> Introduzca el código a buscar: ");
                    Optional<Gate> optionalGate = this.gateService.findGateById(idGate);

                
                    optionalGate.ifPresentOrElse( // Aqui esta la funcion lambda

                        // ¿Es realmente necesario editar paises?
                        // XXX: revisar para poder cambiar el codigo del pais
                        updatedGate -> {
                            System.out.println("Esta es la información actual de la puerta de embarque:\n " + updatedGate);

                            String newGate = Util.getStringInput(">> Ingrese el nuevo número de la puerta de embarque: ");
                            
                            updatedGate.setGateNumber(newGate);

                            this.gateService.updateGate(updatedGate);
                        },
                        
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                int SearchId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<Gate> foundGate = this.gateService.findGateById(SearchId);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundGate.ifPresentOrElse(
                    spottedGate -> { 
                    System.out.println("Esta es la información de la puerta de embarque encontrada:\n" + spottedGate);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o puerta de embarque inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                int deleteId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<Gate> gateToDelete = this.gateService.findGateById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                gateToDelete.ifPresentOrElse(
                    spottedGate -> {
                        this.gateService.deleteGate(deleteId);
                        System.out.println("Puerta de embarque eliminada con éxito");
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o puerta de embarque inexistente");
                    });
        }
    }
}