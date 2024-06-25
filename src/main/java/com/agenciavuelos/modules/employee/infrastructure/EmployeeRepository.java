package com.agenciavuelos.modules.employee.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.employee.domain.Employee;

public interface EmployeeRepository {
    public Optional<Employee> findById(String id);

    public List<Employee> findAll();

    public void save(Employee employee);

    public void update(Employee employee);

    public void delete(String id);
}