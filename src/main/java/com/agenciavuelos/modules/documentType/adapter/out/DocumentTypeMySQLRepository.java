package com.agenciavuelos.modules.documentType.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.documentType.domain.DocumentType;
import com.agenciavuelos.modules.documentType.infrastructure.DocumentTypeRepository;



public class DocumentTypeMySQLRepository implements DocumentTypeRepository{
    private final String url;
    private final String user;
    private final String password;

    public DocumentTypeMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO document_type (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, documentType.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override
    public void update(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE document_type SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, documentType.getName());
                statement.setInt(2, documentType.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override
    public Optional<DocumentType> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM document_type WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        DocumentType documentType = new DocumentType(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
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
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM document_type WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override
    public List<DocumentType> findAll() {
        List<DocumentType> documentTypes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM document_type";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DocumentType documentType = new DocumentType(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    documentTypes.add(documentType);
                }
            }
        } catch (SQLException e) {
        System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return documentTypes;
    }
}