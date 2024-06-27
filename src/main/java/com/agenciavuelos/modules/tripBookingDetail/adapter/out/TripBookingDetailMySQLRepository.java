package com.agenciavuelos.modules.tripBookingDetail.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;
import com.agenciavuelos.modules.tripBookingDetail.infrastructure.TripBookingDetailRepository;

public class TripBookingDetailMySQLRepository implements TripBookingDetailRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripBookingDetailMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(TripBookingDetail tripBookingDetail) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_booking_details (id_trip_booking, id_customer, id_fare, seat_number) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tripBookingDetail.getIdTripBooking());
                statement.setInt(2, tripBookingDetail.getIdCustomer());
                statement.setInt(3, tripBookingDetail.getIdFare());
                statement.setInt(4, tripBookingDetail.getSeatNumber());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TripBookingDetail tripBookingDetail) {
        /* try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trip_booking SET booking_date = ?, id_trip = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripBooking.getBookingDate());
                statement.setInt(2, tripBooking.getIdTrip());
                statement.setInt(3, tripBooking.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } */
    }

    @Override
    public Optional<TripBookingDetail> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking_details WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripBookingDetail tripBookingDetail = new TripBookingDetail(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_trip_booking"),
                            resultSet.getInt("id_customer"),
                            resultSet.getInt("id_fare")
                        );
                        return Optional.of(tripBookingDetail);
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
    public List<TripBookingDetail> findAll() {
        List<TripBookingDetail> tripBookingDetails = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking_details";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripBookingDetail tripBookingDetail = new TripBookingDetail(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_trip_booking"),
                        resultSet.getInt("id_customer"),
                        resultSet.getInt("id_fare")
                    );
                    tripBookingDetails.add(tripBookingDetail);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return tripBookingDetails;
    }

    @Override
    public Optional<TripBookingDetail> findByTripBookingId(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip_booking_details WHERE id_trip_booking = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripBookingDetail tripBookingDetail = new TripBookingDetail(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_trip_booking"),
                            resultSet.getInt("id_customer"),
                            resultSet.getInt("id_fare")
                        );
                        return Optional.of(tripBookingDetail);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Integer> findSeatNumbers(int idTrip) {
        int seatNumber;
        List<Integer> seatNumbers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT tbd.seat_number
                FROM plane p
                INNER JOIN flight_connection fc
                ON fc.plane_plates = p.plates
                INNER JOIN trip t
                ON t.id = fc.id_trip
                INNER JOIN trip_booking tb
                ON tb.id_trip = t.id
                INNER JOIN trip_booking_details tbd
                ON tbd.id_trip_booking = tb.id
                WHERE t.departure_airport_id = fc.id_airport
                AND t.id = ?;
            """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idTrip);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        seatNumber = resultSet.getInt("tbd.seat_number");
                        seatNumbers.add(seatNumber);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seatNumbers;
    }
}