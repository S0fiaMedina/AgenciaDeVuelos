package com.agenciavuelos.modules.airline.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airline.infrastructure.AirlineRepository;
import com.agenciavuelos.modules.airline.domain.Airline;

public class AirlineMySQLRepository implements AirlineRepository {
    private final String url;
    private final String user;
    private final String password;

    public AirlineMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Airline airline) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO airline (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airline.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Airline airline) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE airline SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airline.getName());
                statement.setInt(2, airline.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Airline> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airline WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Airline airline = new Airline(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(airline);
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
            String query = "DELETE FROM airline WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airline> findAll() {
        List<Airline> airlines = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airline";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Airline airline = new Airline(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    airlines.add(airline);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return airlines;
    }
}