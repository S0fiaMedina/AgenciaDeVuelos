package com.agenciavuelos.modules.gate.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.gate.domain.Gate;

public interface GateRepository {
    public Optional<Gate> findById(int id);

    public List<Gate> findAll();

    public void save(Gate gate);

    public void update(Gate gate);

    public void delete(int id);
}