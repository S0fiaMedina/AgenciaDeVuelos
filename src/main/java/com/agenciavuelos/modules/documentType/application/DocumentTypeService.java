package com.agenciavuelos.modules.documentType.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.documentType.domain.DocumentType;
import com.agenciavuelos.modules.documentType.infrastructure.DocumentTypeRepository;


public class DocumentTypeService {
    // inyeccion de repositorio
    private final DocumentTypeRepository documentTypeRepository;

    
    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public List<DocumentType> findAllDocumentTypes(){
        return documentTypeRepository.findAll();
    }

    public Optional<DocumentType>  findDocumentTypeById(int id) {
        Optional<DocumentType> optionalDocumentType = this.documentTypeRepository.findById(id);
        return optionalDocumentType;

    }

    public void deteleDocumentType(int id){
        this.documentTypeRepository.delete(id);
    }

    public void updateDocumentType(DocumentType documentType){
        this.documentTypeRepository.update(documentType);
    }

    public void createDocumentType(DocumentType documentType){
        this.documentTypeRepository.save(documentType);
    }
}