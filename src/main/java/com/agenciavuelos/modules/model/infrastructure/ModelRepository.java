package com.agenciavuelos.modules.model.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.model.domain.Model;



public interface ModelRepository {
    
    public Optional<Model> findById(int id);

    public List<Model> findAllByManufacturer(); // esta funcion busca los modelos por fabricante

    public void save(Model model);

    public void update(Model model);

    public void delete(int id);
}