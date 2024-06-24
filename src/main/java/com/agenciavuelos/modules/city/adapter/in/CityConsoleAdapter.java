package com.agenciavuelos.modules.city.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.city.application.CityService;
import com.agenciavuelos.modules.city.domain.City;
import com.agenciavuelos.modules.country.application.CountryService;
import com.agenciavuelos.modules.country.domain.Country;

public class CityConsoleAdapter {
    private final CityService cityService;
    private final CountryService countryService;

    // lista que contiene las opciones del menu
    private final  String[] cityOptions = { 
        "1. Crear ciudad",
        "2. Actualizar ciudad",
        "3. Buscar ciudad por ID",
        "4. Eliminar pais",
        "5. Salir"
    };

    public CityConsoleAdapter(CityService cityService, CountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE CIUDADES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.cityOptions); 
        return Util.rangeValidator(1, cityOptions.length);
    }

    public void run(){
        int idF;
        int optionSelected = getChoiceFromUser();
        List<Country> countries = countryService.findAllCountries();
        switch (optionSelected) {

            case 1: // CREAR
                // TODO: validacion de no repeticion de codigo de pais
                String name = Util.getStringInput(">> Ingrese el nombre de la ciudad:");
                // System.out.println(countryService.findAllCountries().get(0).getId() + "" + countryService.findAllCountries().get(0).getName());
                for (int i = 0; i <= countries.size() - 1; i++) {
                    System.out.println(countries.get(i).getId() + " - " + countries.get(i).getName());
                }
                do {
                    int idCountry = Util.getIntInput(">> Ingrese el ID del país al que pertenece la ciudad:");
                    idF = cityService.getCountryId(idCountry);
                } while (idF == -1);
                City  city = new City(name, idF);
                this.cityService.createCity(city);
                break;
        
            case 2: // ACTUALIZAR

                List<City> cities = this.cityService.findAllCities();
                // Aqui se podria colocar una funcion para imprimir los manufactureros, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (cities == null || cities.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay ciudades registradas");

                else{
                    String idCity = Util.getStringInput(">> Introduzca el código a buscar: ");
                    Optional<City> optionalCity = this.cityService.findCityById(idCity);

                
                    optionalCity.ifPresentOrElse( // Aqui esta la funcion lambda

                        // ¿Es realmente necesario editar paises?
                        // XXX: revisar para poder cambiar el codigo del pais
                        updatedCity -> {
                            System.out.println("Esta es la información actual de la ciudad:\n " + updatedCity);

                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre de la ciudad: ");
                            
                            updatedCity.setName(newName);

                            this.cityService.updateCity(updatedCity);
                        },
                        
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR POR ID

                String SearchId = Util.getStringInput(">> Introduzca el ID a buscar: ");
                Optional<City> foundCity = this.cityService.findCityById(SearchId);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundCity.ifPresentOrElse(
                    spottedCity -> { 
                    System.out.println("Esta es la información de la ciudad encontrada:\n" + spottedCity);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o ciudad inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR (por id, obviamente)
                String deleteId = Util.getStringInput(">> Introduzca el ID a buscar: ");
                Optional<City> cityToDelete = this.cityService.findCityById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                cityToDelete.ifPresentOrElse(
                    spottedCity -> {
                        this.cityService.deteleCity(deleteId);
                        System.out.println("Ciudad eliminada con éxito");
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o ciudad inexistente");
                    });
        }
    }
}