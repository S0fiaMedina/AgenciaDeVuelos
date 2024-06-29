package com.agenciavuelos.modules.manufacturer.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.manufacturer.domain.Manufacturer;

/*
 * La interfaz que contiene el "contrato" entre la base de datos y el programa
*/
public interface ManufacturerRepository {

    public Optional<Manufacturer> findById(int id);

    public List<Manufacturer> findAll();

    public void save(Manufacturer manufacturer);

    public void update(Manufacturer manufacturer);

    public void delete(int id);

}