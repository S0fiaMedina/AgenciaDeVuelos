package com.agenciavuelos.modules.tripulationRole.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripulationRole.domain.TripulationRole;
import com.agenciavuelos.modules.tripulationRole.infrastructure.TripulationRoleRepository;

public class TripulationRoleService {
    private final TripulationRoleRepository tripulationRoleRepository;

    public TripulationRoleService(TripulationRoleRepository tripulationRoleRepository) {
        this.tripulationRoleRepository = tripulationRoleRepository;
    }

    public List<TripulationRole> findAllTripulationRoles(){
        return tripulationRoleRepository.findAll();
    }

    public Optional<TripulationRole>  findTripulationRoleById(int id) {
        Optional<TripulationRole> optionalTripulationRole = this.tripulationRoleRepository.findById(id);
        return optionalTripulationRole;

    }

    public void deleteTripulationRole(int id){
        this.tripulationRoleRepository.delete(id);
    }

    public void updateTripulationRole(TripulationRole tripulationRole){
        this.tripulationRoleRepository.update(tripulationRole);
    }

    public void createTripulationRole(TripulationRole tripulationRole){
        this.tripulationRoleRepository.save(tripulationRole);
    }
}