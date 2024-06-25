package com.agenciavuelos.modules.revision.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.revision.domain.Revision;

public interface RevisionRepository {
    public Optional<Revision> findById(int id);

    public List<Revision> findAllByPlane(String plate);

    public void save(Revision revision);

    public void update(Revision revision);

    public void delete(int id);
}