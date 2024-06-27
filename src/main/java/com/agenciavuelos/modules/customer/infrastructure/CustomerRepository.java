package com.agenciavuelos.modules.customer.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.customer.domain.Customer;

public interface CustomerRepository {
    public Optional<Customer> findById(int id);

    public Optional<Customer> findByDocumentNumber(int documentNumber);

    public List<Customer> findAll();

    public int save(Customer documentType);

    public void update(Customer documentType);

    public int verifyDocumentNumber(int number);

   
}