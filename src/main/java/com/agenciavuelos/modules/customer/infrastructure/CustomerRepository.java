package com.agenciavuelos.modules.customer.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.customer.domain.Customer;

public interface CustomerRepository {
    public Optional<Customer> findById(int id);

    public List<Customer> findAll();

    public void save(Customer documentType);

    public void update(Customer documentType);

   
}