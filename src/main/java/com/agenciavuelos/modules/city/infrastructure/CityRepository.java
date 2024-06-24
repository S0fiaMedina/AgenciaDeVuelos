package com.agenciavuelos.modules.city.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.city.domain.City;

public interface CityRepository {
    public Optional<City> findById(String id);

    public List<City> findAll();

    public void save(City country);

    public void update(City country);

    public void delete(String id);
}