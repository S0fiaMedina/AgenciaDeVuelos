package com.agenciavuelos.modules.customer.application;

import java.util.List;
import java.util.Optional;



import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.customer.infrastructure.CustomerRepository;
import com.agenciavuelos.modules.documentType.domain.DocumentType;
import com.agenciavuelos.modules.documentType.infrastructure.DocumentTypeRepository;



public class CustomerService {
    // inyeccion de repositorio
    private final CustomerRepository customerRepository;
    private final DocumentTypeRepository documentTypeRepository;

    
    public CustomerService(CustomerRepository customerRepository, DocumentTypeRepository documentTypeRepository) {
        this.customerRepository = customerRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer>  findCustomerById(int id) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        return optionalCustomer;

    }

    public Optional<Customer> findByDocumentNumber(int docNumber) {
        return this.customerRepository.findByDocumentNumber(docNumber);
    }

    public void updateCustomer(Customer documentType){
        this.customerRepository.update(documentType);
    }

    public int createCustomer(Customer documentType){
        return this.customerRepository.save(documentType);
    }

    // verificar ids
    public int verifyDocumentNumber(int number){
        return this.customerRepository.verifyDocumentNumber(number);
    }

    // SERVICIO RELACIONADO A LA RELACION ENTRE CUSTOMER Y TIPO DE DOCUMENTO


    /**
     * Recupera el ID del tipo de documento para el ID dado.
     *
     * @param id El ID del tipo de documento que se desea recuperar.
     * @return El ID del tipo de documento si se encuentra; de lo contrario, devuelve -1.
     */

    public int getDocumentTypeId(int id){
        int foundId = -1;
        Optional<DocumentType> foundDocumentType = documentTypeRepository.findById(id);
        if (foundDocumentType.isPresent()) {
            DocumentType documentType = foundDocumentType.get();
            foundId = documentType.getId();
        }
        return foundId;
    }

    public List<DocumentType> findAllDocumentTypes(){
        return this.documentTypeRepository.findAll();
    }

    
}