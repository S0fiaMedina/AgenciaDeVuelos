package com.agenciavuelos.modules.customer.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public int save(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO customer (name, age, id_document_type, document_number) VALUES (?, ?, ?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, customer.getName());
                statement.setInt(2, customer.getAge());
                statement.setInt(3, customer.getDocumentTypeId());
                statement.setInt(4, customer.getDocumentNumber());
                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id =  generatedKeys.getInt(1);
                    return id;
                }
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return 0;
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

    @Override // pues esta funcion trae el cliente en un formato muy bonito
    public Optional<Customer> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT cu.id, cu.name, cu.age, dt.name, cu.document_number FROM customer  AS cu 
                INNER JOIN document_type AS dt ON dt.id = cu.id_document_type
                WHERE cu.id = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setId(  resultSet.getInt("id") );
                        customer.setName( resultSet.getString("name") );
                        customer.setAge(  resultSet.getInt("age") );  
                        customer.settypeOfDoc( resultSet.getString("dt.name") );
                        customer.setDocumentNumber(resultSet.getInt("document_number") );
                        return Optional.of(customer);
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

    /**
     * Esta funcion verifica si el numero de documento del cliente esta repetido en la base de datos
     * @param number Numero del documento de cliente
     * @return numero de coincidencias de clientes con el mismo numero de id
    */
    @Override
    public int verifyDocumentNumber (int number) {
        int numberOfCoincidences = 0;
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query= """
            SELECT COUNT(id) FROM customer 
            WHERE document_number = ?
            """;
            try(PreparedStatement statement =  connection.prepareStatement(query)){
                statement.setInt(1,number);
                
                // Almacena el numero de coincidencias en una variable 
                ResultSet rs = statement.executeQuery();
                if(rs.next()) {
                    numberOfCoincidences= rs.getInt(1);
                  }
            }
       } catch (Exception e) {
            System.out.println("Se ha producido un error :(, Motivo \n" + e.getMessage() );
       }
        return numberOfCoincidences;
    }

    @Override
    public Optional<Customer> findByDocumentNumber(int documentNumber) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT cu.id, cu.name, cu.age, dt.name, cu.document_number FROM customer  AS cu 
                INNER JOIN document_type AS dt ON dt.id = cu.id_document_type
                WHERE cu.document_number = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, documentNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setId(  resultSet.getInt("id") );
                        customer.setName( resultSet.getString("name") );
                        customer.setAge(  resultSet.getInt("age") );  
                        customer.settypeOfDoc( resultSet.getString("dt.name") );
                        customer.setDocumentNumber(resultSet.getInt("document_number") );
                        return Optional.of(customer);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return Optional.empty();
    }
}