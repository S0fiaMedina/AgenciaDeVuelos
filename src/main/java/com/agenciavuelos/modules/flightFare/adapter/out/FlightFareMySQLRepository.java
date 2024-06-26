package com.agenciavuelos.modules.flightFare.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.flightFare.infrastructure.FlightFareRepository;

public class FlightFareMySQLRepository implements FlightFareRepository {
    private final String url;
    private final String user;
    private final String password;

    public FlightFareMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(FlightFare flightFare) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO flight_fare (description, details, value) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightFare.getDescription());
                statement.setString(2, flightFare.getDetails());
                statement.setDouble(3, flightFare.getValue());
                statement.executeUpdate();
                Util.showSuccess("Se ha registrado correctamente la información");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(FlightFare flightFare) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE flight_fare SET description = ?, details = ?, value = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightFare.getDescription());
                statement.setString(2, flightFare.getDetails());
                statement.setDouble(3, flightFare.getValue());
                statement.setInt(4, flightFare.getId());
                statement.executeUpdate();
                Util.showSuccess("Se ha registrado correctamente la información");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<FlightFare> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM flight_fare WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        FlightFare flightFare = new FlightFare(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getString("details"),
                            resultSet.getDouble("value")
                        );
                        return Optional.of(flightFare);
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
            String query = "DELETE FROM flight_fare WHERE id = ?";
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
    public List<FlightFare> findAll() {
        List<FlightFare> flightFares = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM flight_fare";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    FlightFare flightFare = new FlightFare(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("details"),
                        resultSet.getDouble("value")
                    );
                    flightFares.add(flightFare);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return flightFares;
    }
}