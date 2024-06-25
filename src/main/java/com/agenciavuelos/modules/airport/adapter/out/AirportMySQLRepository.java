package com.agenciavuelos.modules.airport.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airport.infrastructure.AirportRepository;
import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.airport.domain.Airport;

public class AirportMySQLRepository implements AirportRepository {
    private final String url;
    private final String user;
    private final String password;

    public AirportMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Airport airport) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO airport (id, name, id_city) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airport.getId());
                statement.setString(2, airport.getName());
                statement.setInt(3, airport.getIdCity());
                statement.executeUpdate();
                Util.showSuccess("Se ha registrado correctamente la información");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Airport airport) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE airport SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airport.getName());
                statement.setString(2, airport.getId());
                statement.executeUpdate();
                Util.showSuccess("Se ha registrado correctamente la información");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Airport> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airport WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Airport airport = new Airport(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("id_city")
                        );
                        return Optional.of(airport);
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
            String query = "DELETE FROM airport WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
                Util.showSuccess("Se ha eliminado el registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airport> findAll() {
        List<Airport> airports = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airport";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Airport airport = new Airport(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("id_city")
                    );
                    airports.add(airport);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return airports;
    }
}