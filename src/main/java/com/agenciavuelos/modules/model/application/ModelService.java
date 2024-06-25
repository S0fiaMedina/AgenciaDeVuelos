package com.agenciavuelos.modules.model.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.manufacturer.domain.Manufacturer;
import com.agenciavuelos.modules.manufacturer.infrastructure.ManufacturerRepository;
import com.agenciavuelos.modules.model.domain.Model;
import com.agenciavuelos.modules.model.infrastructure.ModelRepository;


public class ModelService {
    private final ModelRepository modelRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ModelService(ModelRepository modelRepository, ManufacturerRepository manufacturerRepository) {
        this.modelRepository = modelRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public Optional<Model> findModelById(int id){
        return this.modelRepository.findById( id);
    }


    public void updateModel(Model model){
        this.modelRepository.update(model);
    }

    public void createModel(Model model){
        this.modelRepository.save(model);
    }

    public void deleteModel(int id){
        this.modelRepository.delete(id);
    }





    // RELACIONADO A FABRICANTES

    public List<Manufacturer> findAllManufacturers(){
        return manufacturerRepository.findAll();
    }



    public int getIdManufacturerId(int id){
        int foundId = -1;
        Optional<Manufacturer> foundManufacturer = manufacturerRepository.findById(id);
        if (foundManufacturer.isPresent()) {
            Manufacturer model = foundManufacturer.get();
            foundId = model.getId();
        }
        return foundId;
    }


    }



    
