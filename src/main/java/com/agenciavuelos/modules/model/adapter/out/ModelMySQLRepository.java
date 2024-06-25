package com.agenciavuelos.modules.model.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.agenciavuelos.modules.model.domain.Model;
import com.agenciavuelos.modules.model.infrastructure.ModelRepository;

public class ModelMySQLRepository implements ModelRepository{

    private final String url;
    private final String user;
    private final String password;

    public ModelMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Model model) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO model (name, manufacturer_id) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, model.getName());
                statement.setInt(2, model.getIdManufacturer());
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override
    public void update(Model model) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                UPDATE model SET name = ?, manufacturer_id = ?
                WHERE id = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, model.getName());
                statement.setInt(2, model.getIdManufacturer());
                statement.setInt(3, model.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override // pues esta funcion trae el cliente en un formato muy bonito
    public Optional<Model> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT * FROM model
                WHERE id = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Model model = new Model(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("manufacturer_id")
                        );
                        return Optional.of(model);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Model> findAllByManufacturer() {
        List<Model> models = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT mo.id, mo.name FROM model AS mo
                INNER JOIN manufacturer AS ma ON mo.manufacturer_id = ma.id
                """;
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Model documentType = new Model(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("manufacturer_id")
                    );
                    models.add(documentType);
                }
            }
        } catch (SQLException e) {
        System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return models;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM model WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

}