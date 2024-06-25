package com.agenciavuelos.modules.plane.adapter.in;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.plane.application.PlaneService;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;

public class PlaneConsoleAdapter {
    private PlaneService planeService;

    private final  String[] planeOptions = { 
        "1. Registrar avion",
        "2. Consultar avion",
        "3. Actualizar avion",
        "4. Eliminar avion",
        "5. Salir"
    };

    public PlaneConsoleAdapter(PlaneService planeService) {
        this.planeService = planeService;
    }

    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE AVIONES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.planeOptions); 
        return Util.rangeValidator(1, planeOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        int idFound;

        switch (planeOptions) {

            /**
             * CASO DE USO #1: Registrar aviones :(
            */
            case 1:
                // registro y validacion de matricula

                String newPlate;
                do {
                    newPlate = Util.getStringInput(">> Introduzca el numero de indentificacion del cliente\n NOTA: debe ser un numero unico"); 
                }while (this.planeService.verifyPlate(newPlate) != 0);

                
                break;

            case 2:
                break;
            
            case 3:
                break;
            
            case 4:
                break;
        
            default:
                break;
        }
    }
}