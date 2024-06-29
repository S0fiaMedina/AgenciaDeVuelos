package com.agenciavuelos.modules.employee.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airline.domain.Airline;
import com.agenciavuelos.modules.airline.infrastructure.AirlineRepository;
import com.agenciavuelos.modules.airport.domain.Airport;
import com.agenciavuelos.modules.airport.infrastructure.AirportRepository;
import com.agenciavuelos.modules.employee.domain.Employee;
import com.agenciavuelos.modules.employee.infrastructure.EmployeeRepository;
import com.agenciavuelos.modules.tripulationRole.domain.TripulationRole;
import com.agenciavuelos.modules.tripulationRole.infrastructure.TripulationRoleRepository;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TripulationRoleRepository tripulationRoleRepository;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TripulationRoleRepository tripulationRoleRepository, AirlineRepository airlineRepository, AirportRepository airportRepository) {
        this.employeeRepository = employeeRepository;
        this.tripulationRoleRepository = tripulationRoleRepository;
        this.airlineRepository = airlineRepository;
        this.airportRepository = airportRepository;
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(String id) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        return optionalEmployee;
    }

    public void deleteEmployee(String id){
        this.employeeRepository.delete(id);
    }

    public void updateEmployee(Employee employee){
        this.employeeRepository.update(employee);
    }

    public void createEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }

    public List<TripulationRole> findAllTripulationRoles() {
        return tripulationRoleRepository.findAll();
    }

    public List<Airline> findAllAirlines() {
        return airlineRepository.findAll();
    }

    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    public int getTripulationRoleId(int id) {
        int idF = -1;
        Optional<TripulationRole> foundTripulationRole = tripulationRoleRepository.findById(id);
        if (foundTripulationRole.isPresent()) {
            TripulationRole tripulationRole = foundTripulationRole.get();
            idF = tripulationRole.getId();
        }
        return idF;
    }

    public int getAirlineId(int id) {
        int idF = -1;
        Optional<Airline> foundAirline = airlineRepository.findById(id);
        if (foundAirline.isPresent()) {
            Airline airline = foundAirline.get();
            idF = airline.getId();
        }
        return idF;
    }

    public String getAirportId(String id) {
        String idF = "";
        Optional<Airport> foundAirport = airportRepository.findById(id);
        if (foundAirport.isPresent()) {
            Airport airport = foundAirport.get();
            idF = airport.getId();
        }
        return idF;
    }

    public String checkId(String id) {
        String idF = "";
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isPresent()) {
            Employee employee = foundEmployee.get();
            idF = employee.getId();
            System.out.println("El ID ya se encuentra registrado");
        }
        return idF;
    }
}