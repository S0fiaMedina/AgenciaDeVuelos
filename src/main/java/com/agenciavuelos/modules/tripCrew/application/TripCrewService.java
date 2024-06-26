package com.agenciavuelos.modules.tripCrew.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.employee.domain.Employee;
import com.agenciavuelos.modules.employee.infrastructure.EmployeeRepository;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;
import com.agenciavuelos.modules.revision.domain.Revision;
import com.agenciavuelos.modules.revision.infrastructure.RevisionRepository;
import com.agenciavuelos.modules.tripCrew.domain.TripCrew;
import com.agenciavuelos.modules.tripCrew.infrastructure.TripCrewRepository;

public class TripCrewService {
    private final TripCrewRepository tripCrewRepository;
    private final EmployeeRepository employeeRepository;

    

    

    public TripCrewService(TripCrewRepository tripCrewRepository, EmployeeRepository employeeRepository) {
        this.tripCrewRepository = tripCrewRepository;
        this.employeeRepository = employeeRepository;
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

    //TODO: logica de viaje
}