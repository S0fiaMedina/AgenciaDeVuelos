package com.agenciavuelos.modules.documentType.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.documentType.domain.DocumentType;

/*
 * La interfaz que contiene el "contrato" entre la base de datos y el programa
*/
public interface DocumentTypeRepository {

    public Optional<DocumentType> findById(int id);

    public List<DocumentType> findAll();

    public void save(DocumentType documentType);

    public void update(DocumentType documentType);

    public void delete(int id);

}