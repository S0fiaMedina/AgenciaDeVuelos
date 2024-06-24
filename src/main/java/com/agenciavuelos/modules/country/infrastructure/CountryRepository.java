package com.agenciavuelos.modules.country.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.country.domain.Country;



public interface CountryRepository {
    /*
 * La interfaz que contiene el "contrato" entre la base de datos y el programa
*/

    public Optional<Country> findById(String id);

    public List<Country> findAll();

    public void save(Country country);

    public void update(Country country);

    public void delete(String id);
}