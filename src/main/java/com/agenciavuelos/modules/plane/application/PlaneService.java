package com.agenciavuelos.modules.plane.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airline.domain.Airline;
import com.agenciavuelos.modules.airline.infrastructure.AirlineRepository;
import com.agenciavuelos.modules.manufacturer.domain.Manufacturer;
import com.agenciavuelos.modules.manufacturer.infrastructure.ManufacturerRepository;
import com.agenciavuelos.modules.model.domain.Model;
import com.agenciavuelos.modules.model.infrastructure.ModelRepository;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;
import com.agenciavuelos.modules.status.domain.Status;
import com.agenciavuelos.modules.status.infrastructure.StatusRepository;



public class PlaneService {
     // inyeccion de repositorio
    private final PlaneRepository planeRepository;
    private final StatusRepository statusRepository;
    private final ModelRepository modelRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final AirlineRepository  airlineRepository;

    
    
    // Debe haber alguna forma de refactorizar esto ;-;

    public PlaneService(PlaneRepository planeRepository, StatusRepository statusRepository,
        ModelRepository modelRepository, ManufacturerRepository manufacturerRepository,
        AirlineRepository airlineRepository) {
        this.planeRepository = planeRepository;
        this.statusRepository = statusRepository;
        this.modelRepository = modelRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.airlineRepository = airlineRepository;
    }

    public List<Plane> findAllPlanes(){
        return planeRepository.findAll();
    }

    public Optional<Plane>  findPlaneById(String plate) {
        Optional<Plane> optionalPlane = this.planeRepository.findById(plate);
        return optionalPlane;

    }
    public void updatePlane(Plane documentType){
        this.planeRepository.update(documentType);
    }

    public void createPlane(Plane documentType){
        this.planeRepository.save(documentType);
    }

    // verificar matricula
    public int verifyPlate(String number){
        return this.planeRepository.verifyPlate(number);
    }

    // FABRICANTE

    public List<Manufacturer> findAllManufacturers(){
        return manufacturerRepository.findAll();
    }

    public int getIdManufacturer(int id){
        int foundId = -1;
        Optional<Manufacturer> foundManufacturer = manufacturerRepository.findById(id);
        if (foundManufacturer.isPresent()) {
            Manufacturer manufacturer = foundManufacturer.get();
            foundId = manufacturer.getId();
        }
        return foundId;
    }

    // MODELO

    public List<Model> getModelsByManufacturer(int id){
        return this.modelRepository.findAllByManufacturer(id);
    }   

    public int getIdModel(int id){
        int foundId = -1;
        Optional<Model> foundModel = modelRepository.findById(id);
        if (foundModel.isPresent()) {
            Model model = foundModel.get();
            foundId = model.getId();
        }
        return foundId;
    }

    // estados
    public List<Status> getAllStatuses(){
        return this.statusRepository.findAll();
    }

    public int getIdStatus(int id){
        int foundId = -1;
        Optional<Status> foundStatus = statusRepository.findById(id);
        if (foundStatus.isPresent()) {
            Status status = foundStatus.get();
            foundId = status.getId();
        }
        return foundId;
    }

    // AEROLINEA
    public List<Airline> getAllAirlines(){
        return this.airlineRepository.findAll();
    }

    public int getIdAirline(int id){
        int foundId = -1;
        Optional<Airline> foundAirline = airlineRepository.findById(id);
        if (foundAirline.isPresent()) {
            Airline airline = foundAirline.get();
            foundId = airline.getId();
        }
        return foundId;
    }



    

}