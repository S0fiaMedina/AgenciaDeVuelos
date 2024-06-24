package com.agenciavuelos.modules.country.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.country.application.CountryService;
import com.agenciavuelos.modules.country.domain.Country;



public class CountryConsoleAdapter {
     private final CountryService countryService;

    // lista que contiene las opciones del menu
    private final  String[] countryOptions = { 
        "1. Crear pais",
        "2. Actualizar pais",
        "3. Buscar pais por ID",
        "4. Eliminar pais",
        "5. Salir"
    };

    public CountryConsoleAdapter(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE PAISES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.countryOptions); 
        return Util.rangeValidator(1, countryOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR
                // TODO: validacion de no repeticion de codigo de pais
                String id = Util.getStringInput(">> Ingrese el codigo del pais. ");
                String name = Util.getStringInput(">> Ingrese el nombre del pais:");
                Country  country = new Country(id,name); // no le prestes atencion al id xd, solo es para que me acepte el parametro
                this.countryService.createCountry(country);
                break;
        
            case 2: // ACTUALIZAR

                List<Country> countries = this.countryService.findAllCountries();
                // Aqui se podria colocar una funcion para imprimir los manufactureros, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (countries == null || countries.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay paises registrados");

                else{
                    String idCountry = Util.getStringInput(">> Introduzca el codigo de  a buscar: ");
                    Optional<Country> optionalManufacturer = this.countryService.findCountryById(idCountry);

                
                    optionalManufacturer.ifPresentOrElse( // Aqui esta la funcion lambda

                        // ¿Es realmente necesario editar paises?
                        // XXX: revisar para poder cambiar el codigo del pais
                        updatedCountry -> {
                            System.out.println("Esta es la información actual del manufacturero:\n " + updatedCountry);

                            String newId = Util.getStringInput(">> Ingrese el nuevo codigo del pais");
                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre del pais: ");

                            
                            updatedCountry.setName(newName);
                            updatedCountry.setId(newId);

                            
                            this.countryService.updateCountry(updatedCountry);
                        },

                        
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                String SearchId = Util.getStringInput(">> Introduzca el codigo a buscar: ");
                Optional<Country> foundCountry = this.countryService.findCountryById(SearchId);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundCountry.ifPresentOrElse(
                    spottedCountry -> { 
                    System.out.println("Esta es la información del fabricante encontrado:\n" + spottedCountry);
                    },
                    ()-> {
                        Util.showWarning("Id no encontrado o fabricante inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                String deleteId = Util.getStringInput(">> Introduzca el id a buscar: ");
                Optional<Country> manufacturerToDelete = this.countryService.findCountryById(deleteId);
              
                // TODO: hacer funcion de validacion de obj nulos
                manufacturerToDelete.ifPresent(spottedCountry -> {
                    this.countryService.deteleCountry(deleteId);
                    System.out.println("fabricante eliminado con exito");
                });
                
                
        }
    }
}