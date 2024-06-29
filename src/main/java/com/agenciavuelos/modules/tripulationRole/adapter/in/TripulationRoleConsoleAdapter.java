package com.agenciavuelos.modules.tripulationRole.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.tripulationRole.application.TripulationRoleService;
import com.agenciavuelos.modules.tripulationRole.domain.TripulationRole;

public class TripulationRoleConsoleAdapter {
    private final TripulationRoleService tripulationRoleService;

    private final  String[] tripulationRoleOptions = { 
        "1. Crear rol",
        "2. Actualizar rol",
        "3. Buscar rol por ID",
        "4. Eliminar rol",
        "5. Salir"
    };

    public TripulationRoleConsoleAdapter(TripulationRoleService tripulationRoleService) {
        this.tripulationRoleService = tripulationRoleService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE ROLES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.tripulationRoleOptions); 
        return Util.rangeValidator(1, tripulationRoleOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR
                // TODO: validacion de no repeticion de codigo de pais
                String name = Util.getStringInput(">> Ingrese el nombre del rol:");
                TripulationRole  tripulationRole = new TripulationRole(name);
                this.tripulationRoleService.createTripulationRole(tripulationRole);
                break;
        
            case 2: // ACTUALIZAR

                List<TripulationRole> tripulationRoles = this.tripulationRoleService.findAllTripulationRoles();
                // Aqui se podria colocar una funcion para imprimir los manufactureros, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (tripulationRoles == null || tripulationRoles.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay roles registrados");

                else{
                    int idTripulationRole = Util.getIntInput(">> Introduzca el código a buscar: ");
                    Optional<TripulationRole> optionalTripulationRole = this.tripulationRoleService.findTripulationRoleById(idTripulationRole);

                
                    optionalTripulationRole.ifPresentOrElse( // Aqui esta la funcion lambda

                        // ¿Es realmente necesario editar paises?
                        // XXX: revisar para poder cambiar el codigo del pais
                        updatedTripulationRole -> {
                            System.out.println("Esta es la información actual del rol:\n " + updatedTripulationRole);

                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre del rol: ");
                            
                            updatedTripulationRole.setName(newName);

                            this.tripulationRoleService.updateTripulationRole(updatedTripulationRole);
                        },
                        
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                int SearchId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<TripulationRole> foundTripulationRole = this.tripulationRoleService.findTripulationRoleById(SearchId);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundTripulationRole.ifPresentOrElse(
                    spottedTripulationRole -> { 
                    System.out.println("Esta es la información del rol encontrado:\n" + spottedTripulationRole);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o rol inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                int deleteId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<TripulationRole> tripulationRoleToDelete = this.tripulationRoleService.findTripulationRoleById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                tripulationRoleToDelete.ifPresentOrElse(
                    spottedTripulationRole -> {
                        this.tripulationRoleService.deleteTripulationRole(deleteId);
                        System.out.println("Rol eliminado con éxito");
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o rol inexistente");
                    });
        }
    }
}