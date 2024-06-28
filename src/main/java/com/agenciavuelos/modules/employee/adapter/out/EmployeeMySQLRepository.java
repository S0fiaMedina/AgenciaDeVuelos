package com.agenciavuelos.modules.employee.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.employee.domain.Employee;
import com.agenciavuelos.modules.employee.infrastructure.EmployeeRepository;

public class EmployeeMySQLRepository implements EmployeeRepository {
    private final String url;
    private final String user;
    private final String password;

    public EmployeeMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO employee (id, name, id_rol, ingress_date, id_airline, id_airport) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, employee.getId());
                statement.setString(2, employee.getName());
                statement.setInt(3, employee.getIdRol());
                statement.setString(4, employee.getIngressDate());
                statement.setInt(5, employee.getIdAirline());
                statement.setString(6, employee.getIdAirport());
                statement.executeUpdate();
                Util.showSuccess("Empleado registrado con exito. ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE employee SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, employee.getName());
                statement.setString(2, employee.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM employee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Employee employee = new Employee(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("id_rol"),
                            resultSet.getString("ingress_date"),
                            resultSet.getInt("id_airline"),
                            resultSet.getString("id_airport")
                        );
                        return Optional.of(employee);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM employee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM employee";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("id_rol"),
                        resultSet.getString("ingress_date"),
                        resultSet.getInt("id_airline"),
                        resultSet.getString("id_airport")
                    );
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return employees;
    }
}