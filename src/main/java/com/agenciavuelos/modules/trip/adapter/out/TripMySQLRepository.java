package com.agenciavuelos.modules.trip.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.trip.infrastructure.TripRepository;

public class TripMySQLRepository implements TripRepository{
    private final String url;
    private final String user;
    private final String password;

    public TripMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Trip trip) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip (tripe_date, price_tripe, departure_airport_id, arrival_airport_id) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, trip.getDate());
                statement.setInt(2, trip.getPrice());
                statement.setString(3, trip.getIdAirportD());
                statement.setString(4, trip.getIdAirportA());
                statement.executeUpdate();
                Util.showSuccess("Se ha registrado correctamente la información");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Trip trip) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trip SET tripe_date = ?, price_tripe = ?, departure_airport_id = ?, arrival_airport_id = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, trip.getDate());
                statement.setInt(2, trip.getPrice());
                statement.setString(3, trip.getIdAirportD());
                statement.setString(4, trip.getIdAirportA());
                statement.setInt(5, trip.getId());
                statement.executeUpdate();
                Util.showSuccess("Se ha registrado correctamente la información");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Trip> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Trip trip = new Trip(
                            resultSet.getInt("id"),
                            resultSet.getString("date"),
                            resultSet.getInt("price"),
                            resultSet.getString("idAirportD"),
                            resultSet.getString("idAirportA")
                        );
                        return Optional.of(trip);
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
            String query = "DELETE FROM trip WHERE id = ?";
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
    public List<Trip> findAll() {
        List<Trip> trips = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Trip trip = new Trip(
                        resultSet.getInt("id"),
                        resultSet.getString("date"),
                        resultSet.getInt("price"),
                        resultSet.getString("idAirportD"),
                        resultSet.getString("idAirportA")
                    );
                    trips.add(trip);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return trips;
    }
} 