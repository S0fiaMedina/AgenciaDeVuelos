package com.agenciavuelos.modules.plane.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.airline.domain.Airline;
import com.agenciavuelos.modules.manufacturer.domain.Manufacturer;
import com.agenciavuelos.modules.model.domain.Model;
import com.agenciavuelos.modules.plane.application.PlaneService;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.status.domain.Status;

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
        System.out.println("GESTOR DE AVIONES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.planeOptions); 
        System.out.println(">> Digite la opcion de su preferencia: ");
        return Util.rangeValidator(1, planeOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();

        switch (optionSelected) {

            case 1:
                this.registerPlane();
                break;

            case 2:
                this.searchForPlane();
                break;

            case 3:
                this.updatePlane();
                break;

            case 4:
                this.deletePlane();
                break;
         
        
            default:
                break;
        }

    }

    /**
     * CASO DE USO #1: Registrar aviones 
    */
    public void registerPlane(){
        String newPlates;
        int newCapacity;
        String newFabricationDate;
        int newManufacturerId;
        int newModelId;
        int newStatusId;
        int newAirlineId;


        // matricula
        int nPlates;
        do {
            newPlates = Util.getStringInput(">> Introduzca la matricula del avion\n NOTA: debe ser  unico"); 
            nPlates = this.planeService.verifyPlate(newPlates);
            if (nPlates != 0){
                Util.showWarning("La matricula debe ser unica.");
            }
        }while (nPlates != 0);

        // capacidad 
        newCapacity = Util.getIntInput(">> Digite la capacidad del avión");

        // FECHA DE FABRICACIÓN
        boolean isCorrect;
        do {
            newFabricationDate = Util.getStringInput(">> Ingrese la fecha de fabricacion: (yyyy- mm - dd)");
            isCorrect = Util.checkDateFormat(newFabricationDate, "yyyy-MM-dd");
        } while (isCorrect == false);

        // Fabricante de avión
        System.out.println(" ------------> FABRICANTES DISPONIBLES <-------------");
        List<Manufacturer>  manufacturers = this.planeService.findAllManufacturers();
        manufacturers.forEach(manufacturer -> { System.out.println(manufacturer); }); 

        int idFound;
        do {
            newManufacturerId = Util.getIntInput(">> Ingrese el id del fabricante del avión: ");
            idFound = this.planeService.getIdManufacturer(newManufacturerId);
        } while (idFound == -1);


        // modelo de avion
        System.out.println(" ------------> MODELOS DEL FABRICANTE DISPONIBLES <-------------");
        List<Model>  models = this.planeService.getModelsByManufacturer(newManufacturerId);
        models.forEach(model -> { System.out.println(model); }); 

        do {
            newModelId = Util.getIntInput(">> Ingrese el id del modelo del avión: ");
            idFound = this.planeService.getIdModel(newModelId);
        } while (idFound == -1);


        // aerolinea
        System.out.println(" ------------> AEROLINEAS DISPONIBLES <-------------");
        List<Airline>  airlines = this.planeService.getAllAirlines();
        airlines.forEach(airline -> { System.out.println(airline); });  // TOD

        do {
            newAirlineId = Util.getIntInput(">> Ingrese el id de la aerolinea del avión: ");
            idFound = this.planeService.getIdAirline(newAirlineId);
        } while (idFound == -1);

        // status
        System.out.println(" ------------> ESTADOS DISPONIBLES <-------------");
        List<Status>  statuses = this.planeService.getAllStatuses();
        statuses.forEach(airline -> { System.out.println(airline); });  

        do {
            newStatusId = Util.getIntInput(">> Ingrese el id del estado del avión: ");
            idFound = this.planeService.getIdStatus(newStatusId);
        } while (idFound == -1);

        // guardado de datos
        Plane newPlane = new Plane(newPlates, newCapacity, newFabricationDate, newModelId, newStatusId, newAirlineId);

        this.planeService.createPlane(newPlane);
        
    }

    /**
     * Caso de Uso 8: Consultar Información de Avión
    */
    public void searchForPlane(){
        String searchPlate;

        searchPlate = Util.getStringInput(">> Ingresa la matricula a buscar: ");
        Optional<Plane> foundPlane = this.planeService.findPlaneById(searchPlate);
            
        foundPlane.ifPresentOrElse(

            spottedPlane -> { 
            System.out.println("Esta es la información del avion encontrado:\n" + spottedPlane);
            },
            ()-> {
                Util.showWarning("Id no encontrado o avion inexistente");
            }
        );
    }

    /**
     * Caso de Uso 15: Consultar Información de Avión
    */
    public void updatePlane(){
        String updatePlate;

        updatePlate = Util.getStringInput(">> Ingresa la matricula a buscar: ");
        Optional<Plane> updatePlane = this.planeService.findPlaneById(updatePlate);
                    
            updatePlane.ifPresentOrElse(

            spottedPlane -> { 
                int updateIdFound;
                int updateCapacity;
                String updateFabricationDate;
                int updateManufacturerId;
                int updateModelId;
                int updateAirlineId;
                int updateStatusId;

                System.out.println("Esta es la información del avion encontrado:\n" + spottedPlane);


                // capacidad del avion
                updateCapacity = Util.getIntInput(">> Digite la capacidad del avión");

                // FECHA DE FABRICACIÓN
                do {
                    updateFabricationDate = Util.getStringInput(">> Ingrese la fecha de fabricacion: (yyyy- mm - dd)");
                } while (Util.checkDateFormat(updateFabricationDate, "yyyy-MM-dd") == false);

                // Fabricante de avión
                System.out.println(" ------------> FABRICANTES DISPONIBLES <-------------");
                List<Manufacturer>  updateManufacturers = this.planeService.findAllManufacturers();
                updateManufacturers.forEach(manufacturer -> { System.out.println(manufacturer); }); 

                do {
                    updateManufacturerId = Util.getIntInput(">> Ingrese el id del fabricante del avión: ");
                    updateIdFound = this.planeService.getIdManufacturer(updateManufacturerId);
                } while (updateIdFound == -1);


                // modelo de avion
                System.out.println(" ------------> MODELOS DEL FABRCANTE DISPONIBLES <-------------");
                List<Model>  modelUpdate = this.planeService.getModelsByManufacturer(updateManufacturerId);
                modelUpdate.forEach(model -> { System.out.println(model); }); 

                do {
                    updateModelId = Util.getIntInput(">> Ingrese el id del modelo del avión: ");
                    updateIdFound = this.planeService.getIdModel(updateModelId);
                } while (updateIdFound == -1);


                // aerolinea
                System.out.println(" ------------> AEROLINEAS DISPONIBLES <-------------");
                List<Airline>  updateAirlines = this.planeService.getAllAirlines();
                updateAirlines.forEach(airline -> { System.out.println(airline); });  // TOD

                do {
                    updateAirlineId = Util.getIntInput(">> Ingrese el id de la aerolinea del avión: ");
                    updateIdFound = this.planeService.getIdAirline(updateAirlineId);
                } while (updateIdFound == -1);

                // status
                System.out.println(" ------------> ESTADOS DISPONIBLES <-------------");
                List<Status>  updateStatus = this.planeService.getAllStatuses();
                updateStatus.forEach(airline -> { System.out.println(airline); });  // TOD

                do {
                    updateStatusId = Util.getIntInput(">> Ingrese el id del estado del avión: ");
                    updateIdFound = this.planeService.getIdStatus(updateStatusId);
                } while (updateIdFound == -1);

                // guardado de datos
                spottedPlane.setCapacity(updateCapacity);
                spottedPlane.setFabricationDate(updateFabricationDate);
                spottedPlane.setIdAirline(updateAirlineId);
                spottedPlane.setIdModel(updateModelId);
                spottedPlane.setIdStatus(updateStatusId);

                System.out.println(spottedPlane);

                this.planeService.updatePlane(spottedPlane);
                
            },
            ()-> {
                Util.showWarning("Id no encontrado o avion inexistente");
            }
        );
    }

                
    /**
     * Caso de Uso 16: Eliminar Avión
    */
    public void deletePlane(){
        String deletePlate; 
        deletePlate = Util.getStringInput(">> Ingresa la matricula  del avion: ");
        Optional<Plane> deletePlane = this.planeService.findPlaneById(deletePlate);
            
        deletePlane.ifPresentOrElse(
        spottedPlane -> { 
            this.planeService.deletePlane(deletePlate);
        },
        ()-> {
            Util.showWarning("Id no encontrado o avion inexistente");
        }
    );
    }
    

    
}