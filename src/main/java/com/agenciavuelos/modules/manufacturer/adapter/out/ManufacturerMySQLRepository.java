package com.agenciavuelos.modules.manufacturer.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.manufacturer.domain.Manufacturer;
import com.agenciavuelos.modules.manufacturer.infrastructure.ManufacturerRepository;

public class ManufacturerMySQLRepository implements ManufacturerRepository{
     private final String url;
    private final String user;
    private final String password;

    

    public ManufacturerMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save( Manufacturer manufacturer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO manufacturer (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, manufacturer.getName());
                statement.executeUpdate();
                Util.showSuccess("Se ha registrado correctamente la información");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE manufacturer SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, manufacturer.getName());
                statement.setInt(2, manufacturer.getId());
                statement.executeUpdate();
                Util.showSuccess("Se ha registrado correctamente la información");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Manufacturer> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM manufacturer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Manufacturer manufacturer = new Manufacturer(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(manufacturer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM manufacturer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                Util.showSuccess("Se ha eliminado el registro");
            }
        } catch (SQLException e) {
            Util.showWarning("No se puede eliminar un registro que se encuentra relacionado con otra tabla");
        }
    }

    @Override
    public List<Manufacturer> findAll() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM manufacturer";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Manufacturer manufacturer = new Manufacturer(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    manufacturers.add(manufacturer);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return manufacturers;
    }

}