package com.agenciavuelos.modules.revision.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.employee.domain.Employee;
import com.agenciavuelos.modules.employee.infrastructure.EmployeeRepository;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;
import com.agenciavuelos.modules.revision.domain.Revision;
import com.agenciavuelos.modules.revision.infrastructure.RevisionRepository;

public class RevisionService {
    private final RevisionRepository revisionRepository;
    private final PlaneRepository planeRepository;
    private final EmployeeRepository employeeRepository;

    

     public RevisionService(RevisionRepository revisionRepository, PlaneRepository planeRepository,
            EmployeeRepository employeeRepository) {
        this.revisionRepository = revisionRepository;
        this.planeRepository = planeRepository;
        this.employeeRepository = employeeRepository;
    }


    public Optional<Revision>  findRevisionById(int id) {
        Optional<Revision> optionalRevision = this.revisionRepository.findById(id);
        return optionalRevision;

    }

    public void deleteRevision(int id){
        this.revisionRepository.delete(id);
    }
    public void updateRevision(Revision plane){
        this.revisionRepository.update(plane);
    }

    public void createRevision(Revision plane){
        this.revisionRepository.save(plane);
    }


    // AVION

    public List<Revision> findAllevisionsByPlane(String planePlate){
        return revisionRepository.findAllByPlane( planePlate);
    }

    // verificar placa de avion
    public String getPlanePlate(String planePlate){
        String foundPlate = "";
        Optional<Plane> foundPlane = planeRepository.findById(planePlate);
        if (foundPlane.isPresent()) {
            Plane plane = foundPlane.get();
            foundPlate = plane.getPlates();
        }
        return foundPlate;
    }




    // verificar empleado
    public String getEmployee(String id){
        String foundId = "";
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isPresent()) {
            Employee employee = foundEmployee.get();
            foundId = employee.getId();
        }
        return foundId;
    }

    


}