package com.agenciavuelos.modules.status.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.status.domain.Status;
import com.agenciavuelos.modules.status.infrastructure.StatusRepository;

public class StatusService {
    private final StatusRepository statusRepository;

    public  StatusService(StatusRepository statusRepository){
        this.statusRepository =  statusRepository;
    }

     public List<Status> findAllStatuses(){
        return statusRepository.findAll();
    }

    public Optional<Status>  findStatusById(int id) {
        Optional<Status> optionalStatus = this.statusRepository.findById(id);
        return optionalStatus;

    }

    public void updateStatus(Status status){
        this.statusRepository.update(status);
    }

    public void createStatus(Status status){
        this.statusRepository.save(status);
    }

    // getters

    public StatusRepository getStatusRepository() {
        return statusRepository;
    }



    
}