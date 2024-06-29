package com.agenciavuelos.modules.tripBooking.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.customer.domain.Customer;
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
    public int save(TripBooking tripBooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_booking (booking_date, id_trip) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, tripBooking.getBookingDate());
                statement.setInt(2, tripBooking.getIdTrip());
                statement.executeUpdate();
                Util.showSuccess("Se ha registrado correctamente la información");
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id =  generatedKeys.getInt(1);
                    return id;
                } 
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return 0;
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
                Util.showSuccess("Se ha registrado correctamente la información");
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
                Util.showSuccess("Se ha eliminado el registro");
            }
        } catch (SQLException e) {
            Util.showWarning("No se puede eliminar un registro que se encuentra relacionado con otra tabla");
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

    @Override
    public List<TripBooking> findByCustomerId(int id) {
        List<TripBooking> tripBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT t.id, c.id, c.name, tb.booking_date, ff.value
                FROM trip t
                INNER JOIN trip_booking tb
                ON tb.id_trip = t.id
                INNER JOIN trip_booking_details tbd
                ON tbd.id_trip_booking = tb.id
                INNER JOIN payment p
                ON p.id_trip_booking = tb.id
                INNER JOIN customer c
                ON c.id = p.id_customer
                INNER JOIN flight_fare ff
                ON ff.id = tbd.id_fare
                WHERE c.id = ?;
            """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TripBooking tripBooking = new TripBooking();
                        tripBooking.setIdTrip(resultSet.getInt("t.id"));
                        tripBooking.setIdCustomer(resultSet.getInt("c.id"));
                        tripBooking.setNameCustomer(resultSet.getString("c.name"));
                        tripBooking.setBookingDate(resultSet.getString("tb.booking_date"));
                        tripBooking.setValueFare(resultSet.getInt("ff.value"));
                        tripBookings.add(tripBooking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookings;
    }

    @Override
    public List<TripBooking> findByTripId(int id) {
        List<TripBooking> tripBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT tp.id, t.id, c.id, c.name, tp.booking_date, ff.value
                FROM trip t
                INNER JOIN trip_booking tp
                ON tp.id_trip = t.id
                INNER JOIN trip_booking_details tbd
                ON tp.id_trip = tbd.id_trip_booking
                INNER JOIN customer c
                ON c.id = tbd.id_customer
                INNER JOIN flight_fare ff
                ON ff.id = tbd.id_fare
                WHERE t.id = ?;
            """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TripBooking tripBooking = new TripBooking();
                        tripBooking.setId( resultSet.getInt("tp.id"));
                        tripBooking.setIdTrip(resultSet.getInt("t.id"));
                        tripBooking.setIdCustomer(resultSet.getInt("c.id"));
                        tripBooking.setNameCustomer(resultSet.getString("c.name"));
                        tripBooking.setBookingDate(resultSet.getString("tp.booking_date"));
                        tripBooking.setValueFare(resultSet.getInt("ff.value"));
                        System.out.println(tripBooking.getId());
                        tripBookings.add(tripBooking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookings;
    }

    @Override
    public List<TripBooking> findBookingById(int id) {
        List<TripBooking> tripBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT tb.id, tb.booking_date, t.tripe_date, c.name, c.document_number, tbd.seat_number
                FROM trip t
                INNER JOIN trip_booking tb
                ON tb.id_trip = t.id
                INNER JOIN trip_booking_details tbd
                ON tbd.id_trip_booking = tb.id
                INNER JOIN customer c
                ON c.id = tbd.id_customer
                WHERE tb.id = ?;
            """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TripBooking tripBooking = new TripBooking();
                        tripBooking.setIdTrip(resultSet.getInt("tb.id"));
                        tripBooking.setBookingDate(resultSet.getString("tb.booking_date"));
                        tripBooking.setTripDate(resultSet.getString("t.tripe_date"));
                        tripBooking.setNameCustomer(resultSet.getString("c.name"));
                        tripBooking.setDocumentCustomer(resultSet.getString("c.document_number"));
                        tripBooking.setSeatNumber(resultSet.getInt("tbd.seat_number"));
                        tripBookings.add(tripBooking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookings;
    }

    @Override
    public List<Integer> findBookingsByCustomerId(int id) {
        List<Integer> tripBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT DISTINCT(tb.id)
                FROM trip_booking tb
                INNER JOIN trip_booking_details tbd
                ON tbd.id_trip_booking = tb.id
                INNER JOIN payment p
                ON p.id_trip_booking = tb.id
                WHERE p.id_customer = ?;
            """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int tripBooking = resultSet.getInt("tb.id");
                        tripBookings.add(tripBooking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookings;
    }

    @Override
    public Optional<TripBooking> findTripBookingOfCustomer(int idCustomer, int idTripBooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT tp.id, t.id, c.id, c.name, tp.booking_date, c.document_number, t.tripe_date, tbd.seat_number
                FROM trip t
                INNER JOIN trip_booking tp
                ON tp.id_trip = t.id
                INNER JOIN trip_booking_details tbd
                ON tp.id_trip = tbd.id_trip_booking
                INNER JOIN customer c
                ON c.id = tbd.id_customer
                INNER JOIN flight_fare ff
                ON ff.id = tbd.id_fare
                WHERE tp.id = ? AND c.document_number = ?;
            """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idTripBooking);
                statement.setInt(2, idCustomer);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        TripBooking tripBooking = new TripBooking();
                        tripBooking.setId( resultSet.getInt("tp.id"));
                        tripBooking.setIdTrip(resultSet.getInt("t.id"));
                        tripBooking.setIdCustomer(resultSet.getInt("c.id"));
                        tripBooking.setNameCustomer(resultSet.getString("c.name"));
                        tripBooking.setBookingDate(resultSet.getString("tp.booking_date"));
                        tripBooking.setDocumentCustomer(resultSet.getString("c.document_number"));
                        tripBooking.setTripDate( resultSet.getString("t.tripe_date"));
                        tripBooking.setSeatNumber( resultSet.getInt("tbd.seat_number"));
                        System.out.println(tripBooking.getId());
                        return Optional.of(tripBooking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Customer> findPassengersOfBooking(int idTripBooking) {
        List<Customer> passengers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT c.id, c.name, c.age, c.document_number, dt.name FROM trip_booking_details AS tbd
                INNER JOIN customer AS c ON c.id = tbd.id_customer
                INNER JOIN document_type AS dt ON c.id_document_type = dt.id
                WHERE tbd.id_trip_booking = ?;
            """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idTripBooking);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setId(  resultSet.getInt("c.id") );
                        customer.setName( resultSet.getString("c.name") );
                        customer.setAge(  resultSet.getInt("c.age") );  
                        customer.settypeOfDoc( resultSet.getString("dt.name") );
                        customer.setDocumentNumber(resultSet.getInt("c.document_number") );
                        passengers.add(customer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }
}