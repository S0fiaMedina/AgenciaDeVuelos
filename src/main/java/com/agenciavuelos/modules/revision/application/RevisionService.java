package com.agenciavuelos.modules.revision.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.employee.domain.Employee;
import com.agenciavuelos.modules.employee.infrastructure.EmployeeRepository;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;
import com.agenciavuelos.modules.revision.domain.Revision;
import com.agenciavuelos.modules.revision.infrastructure.RevisionRepository;
import com.agenciavuelos.modules.revisionDetail.domain.RevisionDetail;
import com.agenciavuelos.modules.revisionDetail.infrastructure.RevisionDetailRepository;

public class RevisionService {
    private final RevisionRepository revisionRepository;
    private final PlaneRepository planeRepository;
    private final EmployeeRepository employeeRepository;
    private final RevisionDetailRepository revisionDetailRepository;

    

     public RevisionService(RevisionRepository revisionRepository, PlaneRepository planeRepository,
        EmployeeRepository employeeRepository, RevisionDetailRepository revisionDetailRepository) {
        this.revisionRepository = revisionRepository;
        this.planeRepository = planeRepository;
        this.employeeRepository = employeeRepository;
        this.revisionDetailRepository = revisionDetailRepository;
    }

    public Optional<Revision> findRevisionById(int id){
        return this.revisionRepository.findByid(id);
    }


    public void deleteRevision(int id){
        this.revisionRepository.delete(id);
    }
    public void updateRevision(Revision revision){
        this.revisionRepository.update(revision);
    }

    public int createRevision(Revision revision){
        return  this.revisionRepository.save(revision);
    }

    // AREVISION _ EMPLOYEE

    public void addEmployeeToRevision(RevisionDetail revisionDetail){
        this.revisionDetailRepository.save(revisionDetail);
    }

    public void updateEmployeeToRevision(RevisionDetail revisionDetail){
        this.revisionDetailRepository.update(revisionDetail);
    }

    public void deleteEmployeeToRevision(int RevisionId){
        this.revisionDetailRepository.delete(RevisionId);
    }

    // ACTUALIZAR EMPLEADO


    // AVION

    public List<Revision> findAllevisionsByPlane(String planePlate){
        return revisionRepository.findAllByPlane( planePlate);
    }

    // verificar placa de avion
    public String getPlatePlane(String planePlate){
        String foundPlate = "";
        Optional<Plane> foundPlane = planeRepository.findById(planePlate);
        if (foundPlane.isPresent()) {
            Plane plane = foundPlane.get();
            foundPlate = plane.getPlates();
        }
        return foundPlate;
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

    


}