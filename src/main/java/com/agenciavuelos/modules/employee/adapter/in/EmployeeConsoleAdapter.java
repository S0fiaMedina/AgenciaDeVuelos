package com.agenciavuelos.modules.employee.adapter.in;

import java.util.List;
// import java.util.Optional;

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

    private final  String[] employeeOptions = { 
        "1. Registrar Empleado",
        "2. Salir"
    };

    public EmployeeConsoleAdapter(EmployeeService employeeService, TripulationRoleService tripulationRoleService, AirlineService airlineService, AirportService airportService) {
        this.employeeService = employeeService;
        this.tripulationRoleService = tripulationRoleService;
        this.airlineService = airlineService;
        this.airportService = airportService;
    }

    /**
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
        String idA;
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

            case 1: 

                do {
                    id = Util.getStringInput(">> Ingrese el ID del empleado: ");
                    idS = employeeService.checkId(id);
                } while (idS != "");
                String name = Util.getStringInput(">> Ingrese el nombre del empleado:");

                System.out.println("------------> ROLES DISPONIBLES <-------------");
                for (int i = 0; i <= roles.size() - 1; i++) {
                    System.out.println(roles.get(i).getId() + " - " + roles.get(i).getName());
                }
                do {
                    idRole = Util.getIntInput(">> Ingrese el ID del rol del empleado:");
                    idF = employeeService.getTripulationRoleId(idRole);
                } while (idF == -1);
                do {
                    date = Util.getStringInput(">> Ingrese la fecha de ingreso del empleado (yyyy-MM-dd):");
                    isCorrect = Util.checkDateFormat(date, "yyyy-MM-dd");
                } while (isCorrect == false);

                System.out.println("------------> AEROLINEAS DISPONIBLES <-------------");
                for (int i = 0; i <= airlines.size() - 1; i++) {
                    System.out.println(airlines.get(i).getId() + " - " + airlines.get(i).getName());
                }
                do {
                    idAirline = Util.getIntInput(">> Ingrese el ID de la aerolinea del empleado:");
                    idF = employeeService.getAirlineId(idAirline);
                } while (idF == -1);

                // si hay muchos aeropuertos disponibles, esto explota xd
                System.out.println("------------> AEROPUERTOS DISPONIBLES <-------------");
                for (int i = 0; i <= airports.size() - 1; i++) {
                    System.out.println(airports.get(i).getId() + " - " + airports.get(i).getName());
                }
                do {
                    idA = Util.getStringInput(">> Ingrese el ID del aeropuerto del empleado: ");
                    idS = employeeService.getAirportId(idA);
                } while (idS == "");
                Employee employee = new Employee(id, name, idRole, date, idAirline, idS);
                this.employeeService.createEmployee(employee);
                break;
        
            case 2:
                break;
        } 
    }
}