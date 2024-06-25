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
        System.out.println("MENU DE AVIONES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.planeOptions); 
        return Util.rangeValidator(1, planeOptions.length);
    }

    public void run(){
        // establecimiento de variables


        int optionSelected = getChoiceFromUser();
        int idFound;
        int capacity;
        String plate;
        String fabricationDate;
        int modelId;
        int airlineId;
        int statusId;
        int manufacturerId;
        boolean isCorrect;
    

        switch (optionSelected) {
            // TODO: validar estados, aerolineas y modelos y fabricantes
            // cuando se vaya a usar la listas solo se muestra el id y placa

            /**
             * CASO DE USO #1: Registrar aviones :(
            */
            case 1:

                // registro y validacion de matricula
                int nPlates;
                do {
                    plate = Util.getStringInput(">> Introduzca la matricula del avion\n NOTA: debe ser  unico"); 
                    nPlates = this.planeService.verifyPlate(plate);
                    if (nPlates != 0){
                        Util.showWarning("La matricula debe ser unica.");
                    }
                }while (nPlates != 0);

                // capacidad del avion
                capacity = Util.getIntInput(">> Digite la capacidad del avión");

                // FECHA DE FABRICACIÓN
                do {
                    fabricationDate = Util.getStringInput(">> Ingrese la fecha de fabricacion: (yyyy- mm - dd)");
                    isCorrect = Util.checkDateFormat(fabricationDate, "yyyy-MM-dd");
                } while (isCorrect == false);

                // Fabricante de avión
                List<Manufacturer>  manufacturers = this.planeService.findAllManufacturers();
                manufacturers.forEach(manufacturer -> { System.out.println(manufacturer); }); 

                do {
                    manufacturerId = Util.getIntInput(">> Ingrese el id del fabricante del avión: ");
                    idFound = this.planeService.getIdManufacturer(manufacturerId);
                } while (idFound == -1);


                // modelo de avion
                List<Model>  models = this.planeService.getModelsByManufacturer(manufacturerId);
                models.forEach(model -> { System.out.println(model); }); 

                do {
                    modelId = Util.getIntInput(">> Ingrese el id del modelo del avión: ");
                    idFound = this.planeService.getIdModel(modelId);
                } while (idFound == -1);


                // aerolinea
                List<Airline>  airlines = this.planeService.getAllAirlines();
                airlines.forEach(airline -> { System.out.println(airline); });  // TOD

                do {
                    airlineId = Util.getIntInput(">> Ingrese el id de la aerolinea del avión: ");
                    idFound = this.planeService.getIdAirline(airlineId);
                } while (idFound == -1);

                // status
                List<Status>  statuses = this.planeService.getAllStatuses();
                statuses.forEach(airline -> { System.out.println(airline); });  // TOD

                do {
                    statusId = Util.getIntInput(">> Ingrese el id del estado del avión: ");
                    idFound = this.planeService.getIdStatus(statusId);
                } while (idFound == -1);

                // guardado de datos
                Plane newPlane = new Plane(plate, capacity, fabricationDate, modelId, statusId, airlineId);

                this.planeService.createPlane(newPlane);
                Util.showWarning("Avion registrado con exito.");

                break;


            /**
             * Caso de Uso 8: Consultar Información de Avión
            */
            case 2:
                
                plate = Util.getStringInput(">> Ingresa la matricula a buscar: ");
                Optional<Plane> foundPlane = this.planeService.findPlaneById(plate);
                    
                foundPlane.ifPresentOrElse(

                    spottedPlane -> { 
                    System.out.println("Esta es la información del avion encontrado:\n" + spottedPlane);
                    },
                    ()-> {
                        Util.showWarning("Id no encontrado o avion inexistente");
                    }
                );
                break;


            /**
             * Caso de Uso 15: Actualizar Información de Avión
            */
            case 3:
                plate = Util.getStringInput(">> Ingresa la matricula a buscar: ");
                Optional<Plane> updatePlane = this.planeService.findPlaneById(plate);
                    
                updatePlane.ifPresentOrElse(

                spottedPlane -> { 
                    int newIdFound;
                    String newPlate;
                    int newCapacity;
                    String newFabricationDate;
                    int newManufacturerId;
                    int newModelId;
                    int newAirlineId;
                    int newStatusId;

                    System.out.println("Esta es la información del avion encontrado:\n" + spottedPlane);

                    int nPlates2;
                    do {
                        newPlate = Util.getStringInput(">> Introduzca la  NUEVA matricula del avion\n NOTA: debe ser  unico"); 
                        nPlates2 = this.planeService.verifyPlate(newPlate);
                        if (nPlates2 != 0){
                            Util.showWarning("La matricula debe ser unica.");
                        }
                    }while (nPlates2 != 0);

                    // capacidad del avion
                    newCapacity = Util.getIntInput(">> Digite la capacidad del avión");

                    // FECHA DE FABRICACIÓN
                    do {
                        newFabricationDate = Util.getStringInput(">> Ingrese la fecha de fabricacion: (yyyy- mm - dd)");
                    } while (Util.checkDateFormat(newFabricationDate, "yyyy-MM-dd") == false);

                    // Fabricante de avión
                    List<Manufacturer>  updateManufacturers = this.planeService.findAllManufacturers();
                    updateManufacturers.forEach(manufacturer -> { System.out.println(manufacturer); }); 

                    do {
                        newManufacturerId = Util.getIntInput(">> Ingrese el id del fabricante del avión: ");
                        newIdFound = this.planeService.getIdManufacturer(newManufacturerId);
                    } while (newIdFound == -1);


                    // modelo de avion
                    List<Model>  modelUpdate = this.planeService.getModelsByManufacturer(newManufacturerId);
                    modelUpdate.forEach(model -> { System.out.println(model); }); 

                    do {
                        newModelId = Util.getIntInput(">> Ingrese el id del modelo del avión: ");
                        newIdFound = this.planeService.getIdModel(newModelId);
                    } while (newIdFound == -1);


                    // aerolinea
                    List<Airline>  updateAirlines = this.planeService.getAllAirlines();
                    updateAirlines.forEach(airline -> { System.out.println(airline); });  // TOD

                    do {
                        newAirlineId = Util.getIntInput(">> Ingrese el id de la aerolinea del avión: ");
                        newIdFound = this.planeService.getIdAirline(newAirlineId);
                    } while (newIdFound == -1);

                    // status
                    List<Status>  updateStatus = this.planeService.getAllStatuses();
                    updateStatus.forEach(airline -> { System.out.println(airline); });  // TOD

                    do {
                        newStatusId = Util.getIntInput(">> Ingrese el id del estado del avión: ");
                        newIdFound = this.planeService.getIdStatus(newStatusId);
                    } while (newIdFound == -1);

                    // guardado de datos
                    spottedPlane.setPlates(newPlate);
                    spottedPlane.setCapacity(newCapacity);
                    spottedPlane.setFabricationDate(newFabricationDate);
                    spottedPlane.setIdAirline(newAirlineId);
                    spottedPlane.setIdModel(newModelId);
                    spottedPlane.setIdStatus(newStatusId);

                    System.out.println(spottedPlane);

                    this.planeService.updatePlane(spottedPlane);
                    Util.showWarning("Avion actualizado exitosamente");
                },
                ()-> {
                    Util.showWarning("Id no encontrado o avion inexistente");
                }
            );
                break;
            
            /**
             * Caso de Uso 16: Eliminar Avión
            */
            case 4:
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
         
        
            default:
                break;
        }
    }
}