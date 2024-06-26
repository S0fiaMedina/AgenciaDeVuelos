package com.agenciavuelos.modules.tripBooking.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.tripBooking.infrastructure.TripBookingRepository;

public class TripBookingMySQLRepository implements TripBookingRepository{
    private final String url;
    private final String user;
    private final String password;

    public TripBookingMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(TripBooking tripBooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_booking (booking_date, id_trip) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripBooking.getBookingDate());
                statement.setInt(2, tripBooking.getIdTrip());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TripBooking tripBooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trip_booking SET booking_date = ?, id_trip = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripBooking.getBookingDate());
                statement.setInt(2, tripBooking.getIdTrip());
                statement.setInt(3, tripBooking.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<TripBooking> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripBooking tripBooking = new TripBooking(
                            resultSet.getInt("id"),
                            resultSet.getString("booking_date"),
                            resultSet.getInt("id_trip")
                        );
                        return Optional.of(tripBooking);
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
            String query = "DELETE FROM trip_booking WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TripBooking> findAll() {
        List<TripBooking> tripBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripBooking tripBooking = new TripBooking(
                        resultSet.getInt("id"),
                        resultSet.getString("booking_date"),
                        resultSet.getInt("id_trip")
                    );
                    tripBookings.add(tripBooking);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return tripBookings;
    }    
}