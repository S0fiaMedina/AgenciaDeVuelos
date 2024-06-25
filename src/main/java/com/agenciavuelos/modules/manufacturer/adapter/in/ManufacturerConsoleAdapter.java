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
        "1. Crear fabricante",
        "2. Actualizar fabricante",
        "3. Buscar fabricante por ID",
        "4. Eliminar fabricante",
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
        System.out.println("MENU DE FABRICANTE");
        System.out.println("-------------------------------------");
        Util.printOptions(this.manufacturerOptions); 
        return Util.rangeValidator(1, manufacturerOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR
                String name = Util.getStringInput(">> Ingrese el nombre del fabricante:");
                Manufacturer  manufacturer = new Manufacturer(0,name); // no le prestes atencion al id xd, solo es para que me acepte el parametro
                this.manufacturerService.createManufacturer(manufacturer);
                break;
        
            case 2: // ACTUALIZAR

                List<Manufacturer> manufacturers = this.manufacturerService.findAllManufacturers();
                // Aqui se podria colocar una funcion para imprimir los manufactureros, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (manufacturers == null || manufacturers.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay fabricantes registrados");

                else{
                    int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                    Optional<Manufacturer> optionalManufacturer = this.manufacturerService.findManufacturerById(id);

                
                    optionalManufacturer.ifPresentOrElse( // Aqui esta la funcion lambda

                        // Acción si el fabricante está presente
                        updatedmManufacturer -> {
                            System.out.println("Esta es la información actual del fabricante:\n " + updatedmManufacturer);
                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre del manufacturero: ");

                            // Cambiar el nombre del fabricante
                            updatedmManufacturer.setName(newName);

                            // Actualizar el fabricante en la base de datos
                            this.manufacturerService.updateManufacturer(updatedmManufacturer);
                        },

                        // Acción si el fabricante no está presente (ID no encontrado)
                        () -> {
                            Util.showWarning("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                Optional<Manufacturer> foundManufacturer = this.manufacturerService.findManufacturerById(id);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundManufacturer.ifPresentOrElse(
                    spottedManufacturer -> { // Si el fabricante fue encontrado...
                    System.out.println("Esta es la información del fabricante encontrado:\n" + spottedManufacturer);
                    },
                    ()-> {
                        Util.showWarning("Id no encontrado o fabricante inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                int deleteId = Util.getIntInput(">> Introduzca el id a buscar: ");
                Optional<Manufacturer> manufacturerToDelete = this.manufacturerService.findManufacturerById(deleteId);
                // Utilizar ifPresent para manejar el caso cuando el fabricante está presente
                // TODO: hacer funcion de validacion de obj nulos
                manufacturerToDelete.ifPresentOrElse(
                    spottedManufacturer -> {
                        this.manufacturerService.deteleManufacturer(deleteId);
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o fabricante inexistente");
                    }); 
        }
    }

}