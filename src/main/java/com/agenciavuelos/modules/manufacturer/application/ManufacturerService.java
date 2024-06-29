package com.agenciavuelos.modules.manufacturer.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.manufacturer.domain.Manufacturer;
import com.agenciavuelos.modules.manufacturer.infrastructure.ManufacturerRepository;

public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;


    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }


    public List<Manufacturer> findAllManufacturers(){
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer>  findManufacturerById(int id) {
        Optional<Manufacturer> optionalManufacturer = this.manufacturerRepository.findById(id);
        return optionalManufacturer;

    }

    public void deteleManufacturer(int id){
        this.manufacturerRepository.delete(id);
    }

    public void updateManufacturer(Manufacturer manufacturer){
        this.manufacturerRepository.update(manufacturer);
    }

    public void createManufacturer(Manufacturer manufacturer){
        this.manufacturerRepository.save(manufacturer);
    }


    // getters

    public ManufacturerRepository getManufacturerRepositoryById() {
        return this.manufacturerRepository;
    }


}