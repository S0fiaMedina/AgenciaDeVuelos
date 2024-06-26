package com.agenciavuelos.modules.revisionDetail.infrastructure;
import com.agenciavuelos.modules.revisionDetail.domain.RevisionDetail;

public interface RevisionDetailRepository {

    public void save(RevisionDetail revisionDetail);

    public void update(RevisionDetail revisionDetail);

    public void delete(int id);
}