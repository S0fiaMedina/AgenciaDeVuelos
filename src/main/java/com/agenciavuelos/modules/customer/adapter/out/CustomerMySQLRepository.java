package com.agenciavuelos.modules.customer.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.customer.infrastructure.CustomerRepository;

public class CustomerMySQLRepository  implements CustomerRepository{
    private final String url;
    private final String user;
    private final String password;

    public CustomerMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO customer (name, age, id_document_type, document_number) VALUES (?, ?, ?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customer.getName());
                statement.setInt(2, customer.getAge());
                statement.setInt(3, customer.getDocumentTypeId());
                statement.setInt(4, customer.getDocumentNumber());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                UPDATE customer SET name = ?, age = ?, id_document_type = ?, document_number = ? 
                WHERE id = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customer.getName());
                statement.setInt(2, customer.getAge());
                statement.setInt(3, customer.getDocumentTypeId());
                statement.setInt(4, customer.getDocumentNumber());
                statement.setInt(5, customer.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override
    public Optional<Customer> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT id, name, age, id_document_type, document_number FROM customer 
                WHERE id = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer documentType = new Customer(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getInt("id_document_type"),
                            resultSet.getInt("document_number")
                        );
                        return Optional.of(documentType);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT id, name, age, id_document_type, document_number FROM customer 
                """;
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Customer documentType = new Customer(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getInt("id_document_type"),
                            resultSet.getInt("document_number")
                    );
                    customers.add(documentType);
                }
            }
        } catch (SQLException e) {
        System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return customers;
    }
}