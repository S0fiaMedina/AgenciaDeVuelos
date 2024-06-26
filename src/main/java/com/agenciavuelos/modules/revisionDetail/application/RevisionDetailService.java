package com.agenciavuelos.modules.revisionDetail.application;


import com.agenciavuelos.modules.revisionDetail.domain.RevisionDetail;
import com.agenciavuelos.modules.revisionDetail.infrastructure.RevisionDetailRepository;

public class RevisionDetailService {
    private final RevisionDetailRepository revisionDetailRepository;

    public RevisionDetailService(RevisionDetailRepository revisionDetailRepository) {
        this.revisionDetailRepository = revisionDetailRepository;
    }

    

    public void deleteRevisionEmployee(int idRevision){
        this.revisionDetailRepository.delete(idRevision);
    }

    public void updateRevisionEmployee(RevisionDetail revisionDetail){
        this.revisionDetailRepository.update(revisionDetail);
    }

    public void createRevisionEmployee(RevisionDetail revisionDetail){
        this.revisionDetailRepository.save(revisionDetail);
    }


}