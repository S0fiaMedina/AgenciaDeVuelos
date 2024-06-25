package com.agenciavuelos.modules.status.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.status.domain.Status;

public interface StatusRepository {

    public Optional<Status> findById(int id);

    public List<Status> findAll();

    public void save(Status documentType);

    public void update(Status documentType);

}