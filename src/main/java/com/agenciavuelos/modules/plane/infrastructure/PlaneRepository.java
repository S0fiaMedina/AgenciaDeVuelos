package com.agenciavuelos.modules.plane.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.plane.domain.Plane;

public interface PlaneRepository {
    
    public Optional<Plane> findById(String plate);

    public List<Plane> findAll();

    public void save(Plane plane);

    public void update(Plane plane);

    public int verifyPlate(String plate);

    public void delete(String plate);

    public Optional<Plane> findByTrip(int idTrip);
}