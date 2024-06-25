package com.agenciavuelos.modules.status.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.status.application.StatusService;
import com.agenciavuelos.modules.status.domain.Status;

public class StatusConsoleAdapter {

    private final StatusService statusService;
    private final  String[] statusOptions = { 
        "1. Crear estado",
        "2. Actualizar estado",
        "3. Buscar estado por ID",
        "5. Salir"
    };

    public StatusConsoleAdapter(StatusService statusService){
        this.statusService = statusService;
    }

    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE ESTADOS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.statusOptions); 
        return Util.rangeValidator(1, statusOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR

                // solicita los datos
                String name = Util.getStringInput(">> Ingrese el nombre del tipo de documento:");
                Status  status = new Status(name); 

                //guarda
                this.statusService.createStatus(status);

                break;

            case 2: // ACTUALIZAR

                // solicita los datos
                List<Status> statuses = this.statusService.findAllStatuses();
               

                if (statuses == null || statuses.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay estados registrados");

                else{
                    int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                    Optional<Status> optionalStatus = this.statusService.findStatusById(id);

                
                    optionalStatus.ifPresentOrElse( // Aqui esta la funcion lambda

                        // Acción si el estado está presente
                        updatedStatus -> {
                            System.out.println("Esta es la información actual del estado:\n " + updatedStatus);
                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre del estado: ");

                            // hace cambios
                            updatedStatus.setName(newName);

                            // Actualizar el estado en la base de datos
                            this.statusService.updateStatus(updatedStatus);
                        },

                        // Acción si el estado no está presente (ID no encontrado)
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR
                int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                Optional<Status> foundStatus = this.statusService.findStatusById(id);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundStatus.ifPresentOrElse(
                    spottedStatus -> { 
                    System.out.println("Esta es la información del estado encontrado:\n" + spottedStatus);
                    },
                    ()-> {
                        Util.showWarning("Id no encontrado o estado inexistente");
                    }
                
                );
                break;
        }
    }
}