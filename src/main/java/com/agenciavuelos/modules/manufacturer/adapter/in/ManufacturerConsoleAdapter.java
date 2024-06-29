package com.agenciavuelos.modules.manufacturer.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.manufacturer.application.ManufacturerService;
import com.agenciavuelos.modules.manufacturer.domain.Manufacturer;


public class ManufacturerConsoleAdapter {
     private final ManufacturerService manufacturerService;

    // lista que contiene las opciones del menu
    private final  String[] manufacturerOptions = { 
        "1. Registrar Fabricante",
        "2. Actualizar Fabricante",
        "3. Consultar Fabricante por ID",
        "4. Eliminar Fabricante",
        "5. Salir"
    };

    public ManufacturerConsoleAdapter(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MODULO DE FABRICANTES DE AVIONES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.manufacturerOptions); 
        return Util.rangeValidator(1, manufacturerOptions.length);
    }

    public void run(){
        int optionSelected;
        do {
            optionSelected = getChoiceFromUser();
            switch (optionSelected) {

                case 1: 
                    String name = Util.getStringInput(">> Ingrese el nombre del fabricante:");
                    Manufacturer manufacturer = new Manufacturer(name);
                    this.manufacturerService.createManufacturer(manufacturer);
                    break;
            
                case 2: 
                    List<Manufacturer> manufacturers = this.manufacturerService.findAllManufacturers();
                    

                    if (manufacturers == null || manufacturers.isEmpty()  ) 
                        Util.showWarning("No hay fabricantes registrados");

                    else{
                        int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                        Optional<Manufacturer> optionalManufacturer = this.manufacturerService.findManufacturerById(id);

                    
                        optionalManufacturer.ifPresentOrElse( 

                            
                            updatedmManufacturer -> {
                                System.out.println("Esta es la información actual del fabricante:\n " + updatedmManufacturer);
                                String newName = Util.getStringInput(">> Ingrese el nuevo nombre del manufacturero: ");

                                
                                updatedmManufacturer.setName(newName);

                                
                                this.manufacturerService.updateManufacturer(updatedmManufacturer);
                            },

                            
                            () -> {
                                Util.showWarning("ID no encontrado");
                            });
                        }
                    break;

                case 3: 
                    int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                    Optional<Manufacturer> foundManufacturer = this.manufacturerService.findManufacturerById(id);
                    
                    
                    foundManufacturer.ifPresentOrElse(
                        spottedManufacturer -> { // Si el fabricante fue encontrado...
                        System.out.println("Esta es la información del fabricante encontrado:\n" + spottedManufacturer);
                        },
                        ()-> {
                            Util.showWarning("Id no encontrado o fabricante inexistente");
                        }
                    
                    );
                    break;
                
                case 4:
                    int deleteId = Util.getIntInput(">> Introduzca el id a buscar: ");
                    Optional<Manufacturer> manufacturerToDelete = this.manufacturerService.findManufacturerById(deleteId);

                    manufacturerToDelete.ifPresentOrElse(
                        spottedManufacturer -> {
                            this.manufacturerService.deteleManufacturer(deleteId);
                        },
                        () -> {
                            Util.showWarning("ID no encontrado o fabricante inexistente");
                        });
                    break;
                case 5:
                    break;
            }
        } while (optionSelected != 5);
        
    }

}