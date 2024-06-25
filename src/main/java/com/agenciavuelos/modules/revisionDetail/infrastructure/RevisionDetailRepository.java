package com.agenciavuelos.modules.revisionDetail.infrastructure;


import java.util.Optional;

import com.agenciavuelos.modules.revision.domain.Revision;

public interface RevisionDetailRepository {
    public Optional<Revision> findById(int id);

    public void save(Revision revision);

    public void update(Revision revision);

    public void delete(int id);
}