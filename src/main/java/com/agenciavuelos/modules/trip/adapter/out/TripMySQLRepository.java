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
                statement.setDouble(2, trip.getPrice());
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
                statement.setDouble(2, trip.getPrice());
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
            String query = """
                    SELECT t.id, t.tripe_date, t.price_tripe, t.departure_airport_id, t.arrival_airport_id, c1.name, c2.name
                    FROM trip t
                    INNER JOIN airport a1
                    ON a1.id = t.departure_airport_id
                    INNER JOIN airport a2
                    ON a2.id = t.arrival_airport_id
                    INNER JOIN city c1
                    ON c1.id = a1.id_city
                    INNER JOIN city c2
                    ON c2.id = a2.id_city
                    WHERE t.id = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Trip trip = new Trip();
                        trip.setId(resultSet.getInt("t.id"));
                        trip.setDate(resultSet.getString("t.tripe_date"));
                        trip.setPrice(resultSet.getDouble("t.price_tripe"));
                        trip.setIdAirportD(resultSet.getString("t.departure_airport_id"));
                        trip.setIdAirportA(resultSet.getString("t.arrival_airport_id"));
                        trip.setNameCityD(resultSet.getString("c1.name"));
                        trip.setNameCityA(resultSet.getString("c2.name"));
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
                        resultSet.getString("tripe_date"),
                        resultSet.getDouble("price_tripe"),
                        resultSet.getString("departure_airport_id"),
                        resultSet.getString("arrival_airport_id")
                    );
                    trips.add(trip);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return trips;
    }

    @Override
    public List<Trip> searchTrips(String nameCityD, String nameCityA, String departureDate) {
        List<Trip> trips = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT t.id, t.tripe_date, t.price_tripe, c1.name, c2.name
                FROM trip t
                INNER JOIN airport a1
                ON a1.id = t.departure_airport_id
                INNER JOIN airport a2
                ON a2.id = t.arrival_airport_id
                INNER JOIN city c1
                ON c1.id = a1.id_city
                INNER JOIN city c2
                ON c2.id = a2.id_city
                WHERE c1.name = ?
                AND c2.name = ?
                AND t.tripe_date = ?;
            """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nameCityD);
                statement.setString(2, nameCityA);
                statement.setString(3, departureDate);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Trip trip = new Trip();
                        trip.setId(resultSet.getInt("t.id"));
                        trip.setDate(resultSet.getString("t.tripe_date"));
                        trip.setPrice(resultSet.getDouble("t.price_tripe"));
                        trip.setNameCityD(resultSet.getString("c1.name"));
                        trip.setNameCityA(resultSet.getString("c2.name"));
                        trips.add(trip);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }
} 