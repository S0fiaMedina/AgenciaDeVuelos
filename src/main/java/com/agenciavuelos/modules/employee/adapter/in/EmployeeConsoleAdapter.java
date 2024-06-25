package com.agenciavuelos.modules.employee.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.airline.application.AirlineService;
import com.agenciavuelos.modules.airline.domain.Airline;
import com.agenciavuelos.modules.airport.application.AirportService;
import com.agenciavuelos.modules.airport.domain.Airport;
import com.agenciavuelos.modules.employee.application.EmployeeService;
import com.agenciavuelos.modules.employee.domain.Employee;
import com.agenciavuelos.modules.tripulationRole.application.TripulationRoleService;
import com.agenciavuelos.modules.tripulationRole.domain.TripulationRole;

public class EmployeeConsoleAdapter {
    private final EmployeeService employeeService;
    private final TripulationRoleService tripulationRoleService;
    private final AirlineService airlineService;
    private final AirportService airportService;

    // lista que contiene las opciones del menu
    private final  String[] employeeOptions = { 
        "1. Crear empleado",
        "2. Actualizar empleado",
        "3. Buscar empleado por ID",
        "4. Eliminar empleado",
        "5. Salir"
    };

    public EmployeeConsoleAdapter(EmployeeService employeeService, TripulationRoleService tripulationRoleService, AirlineService airlineService, AirportService airportService) {
        this.employeeService = employeeService;
        this.tripulationRoleService = tripulationRoleService;
        this.airlineService = airlineService;
        this.airportService = airportService;
    }

    /**
     * Muestra un menú de opciones de fabricantes y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE EMPLEADOS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.employeeOptions); 
        return Util.rangeValidator(1, employeeOptions.length);
    }

    public void run(){
        String id;
        int idF;
        int idRole;
        String date;
        int idAirline;
        String idS = "";
        boolean isCorrect = true;
        int optionSelected = getChoiceFromUser();
        List<TripulationRole> roles = tripulationRoleService.findAllTripulationRoles();
        List<Airline> airlines = airlineService.findAllAirlines();
        List<Airport> airports = airportService.findAllAirports();
        switch (optionSelected) {

            case 1: // CREAR
                // TODO: validacion de no repeticion de codigo de pais
                do {
                    id = Util.getStringInput(">> Ingrese el ID del empleado: ");
                    idS = employeeService.checkId(id);
                } while (idS != "");
                String name = Util.getStringInput(">> Ingrese el nombre del empleado:");
                // System.out.println(cityService.findAllCountries().get(0).getId() + "" + cityService.findAllCountries().get(0).getName());
                for (int i = 0; i <= roles.size() - 1; i++) {
                    System.out.println(roles.get(i).getId() + " - " + roles.get(i).getName());
                }
                do {
                    idRole = Util.getIntInput(">> Ingrese el ID del rol del empleado:");
                    idF = employeeService.getTripulationRoleId(idRole);
                } while (idF == -1);
                do {
                    date = Util.getStringInput(">> Ingrese la fecha de ingreso del empleado:");
                    isCorrect = Util.checkDateFormat(date, "yyyy-MM-dd");
                } while (isCorrect == false);
                for (int i = 0; i <= airlines.size() - 1; i++) {
                    System.out.println(airlines.get(i).getId() + " - " + airlines.get(i).getName());
                }
                do {
                    idAirline = Util.getIntInput(">> Ingrese el ID de la aerolinea del empleado:");
                    idF = employeeService.getAirlineId(idAirline);
                } while (idF == -1);
                for (int i = 0; i <= airports.size() - 1; i++) {
                    System.out.println(airports.get(i).getId() + " - " + airports.get(i).getName());
                }
                do {
                    id = Util.getStringInput(">> Ingrese el ID del aeropuerto del empleado: ");
                    idS = employeeService.getAirportId(id);
                } while (idS == "");
                Employee employee = new Employee(id, name, idRole, date, idAirline, idS);
                this.employeeService.createEmployee(employee);
                break;
        
            case 2: // ACTUALIZAR
                /*
                List<Airport> employees = this.employeeService.findAllAirports();
                // Aqui se podria colocar una funcion para imprimir los manufactureros, pero me da pereza xd, esto es solo para mostrar
                // esa linea se podria eliminar luego para mejorar rendimeinto

                if (employees == null || employees.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay aeropuertos registrados");

                else{
                    String idAirport = Util.getStringInput(">> Introduzca el código a buscar: ");
                    Optional<Airport> optionalAirport = this.employeeService.findAirportById(idAirport);

                
                    optionalAirport.ifPresentOrElse( // Aqui esta la funcion lambda

                        // ¿Es realmente necesario editar paises?
                        // XXX: revisar para poder cambiar el codigo del pais
                        updatedAirport -> {
                            System.out.println("Esta es la información actual del aeropuerto:\n " + updatedAirport);

                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre del aeropuerto: ");
                            
                            updatedAirport.setName(newName);

                            this.employeeService.updateAirport(updatedAirport);
                        },
                        
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break; */

            case 3: // BUSCAR POR ID
                /*
                String SearchId = Util.getStringInput(">> Introduzca el ID a buscar: ");
                Optional<Airport> foundAirport = this.employeeService.findAirportById(SearchId);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundAirport.ifPresentOrElse(
                    spottedAirport -> { 
                    System.out.println("Esta es la información del aeropuerto encontrado:\n" + spottedAirport);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o aerppuerto inexistente");
                    }
                
                );
                break; */
            
            case 4: // ELIMINAR (por id, obviamente)
                /*
                String deleteId = Util.getStringInput(">> Introduzca el ID a buscar: ");
                Optional<Airport> employeeToDelete = this.employeeService.findAirportById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                employeeToDelete.ifPresentOrElse(
                    spottedAirport -> {
                        this.employeeService.deteleAirport(deleteId);
                        System.out.println("Aeropuerto eliminado con éxito");
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o aeropuerto inexistente");
                    }); */
        } 
    }
}