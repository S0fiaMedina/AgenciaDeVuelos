package com.agenciavuelos.modules.tripCrew.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.employee.domain.Employee;
import com.agenciavuelos.modules.employee.infrastructure.EmployeeRepository;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.trip.infrastructure.TripRepository;
import com.agenciavuelos.modules.tripCrew.domain.TripCrew;
import com.agenciavuelos.modules.tripCrew.infrastructure.TripCrewRepository;

public class TripCrewService {
    private final TripCrewRepository tripCrewRepository;
    private final EmployeeRepository employeeRepository;
    private final TripRepository tripRepository;
    

    

    public TripCrewService(TripCrewRepository tripCrewRepository, EmployeeRepository employeeRepository, TripRepository tripRepository) {
        this.tripCrewRepository = tripCrewRepository;
        this.employeeRepository = employeeRepository;
        this.tripRepository = tripRepository;
    }

    public void addTripulation(TripCrew tripCrew){
        this.tripCrewRepository.addTripulationToTrip(tripCrew);
    }

    public List<TripCrew> getTripulation(int idTrip){
        return this.tripCrewRepository.getTripulationFromTrip(idTrip);
    }

    // verificar empleado
    public String getIdEmployee(String id){
        String foundId = "";
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isPresent()) {
            Employee employee = foundEmployee.get();
            foundId = employee.getId();
        }
        return foundId;
    }

    //verificacion de viaje
    public int getidTrip(int id){
        int foundId = -1;
        Optional<Trip> foundTrip = tripRepository.findById(id);

        if (foundTrip.isPresent()) {
            Trip trip = foundTrip.get();
            foundId = trip.getId();
        }
        return foundId;
    }

    // trae todos los empleados 
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // trae todos los trayectos
    public List<Trip> getAllTrips(){
        return this.tripRepository.findAll();
    }

}