package com.agenciavuelos.modules.tripulationRole.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripulationRole.domain.TripulationRole;

public interface TripulationRoleRepository {
    public Optional<TripulationRole> findById(int id);

    public List<TripulationRole> findAll();

    public void save(TripulationRole tripulationRole);

    public void update(TripulationRole tripulationRole);

    public void delete(int id);
}