package com.agenciavuelos.modules.payment.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.payment.domain.Payment;
import com.agenciavuelos.modules.payment.infrastructure.PaymentRepository;

public class PaymentMySQLRepository implements PaymentRepository{
    private final String url;
    private final String user;
    private final String password;

    public PaymentMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Payment payment) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO payment (id_customer, id_payment_form, id_trip_booking) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, payment.getIdCustomer());
                statement.setInt(2, payment.getIdPaymentForm());
                statement.setInt(3, payment.getIdTripBooking());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payment payment) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE payment SET id_customer = ?, id_payment_form = ?, id_trip_booking = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, payment.getIdCustomer());
                statement.setInt(2, payment.getIdPaymentForm());
                statement.setInt(3, payment.getIdTripBooking());
                statement.setInt(4, payment.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Payment> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM payment WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Payment payment = new Payment(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_customer"),
                            resultSet.getInt("id_payment_form"),
                            resultSet.getInt("id_trip_booking")
                        );
                        return Optional.of(payment);
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
            String query = "DELETE FROM payment WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM payment";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Payment payment = new Payment(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_customer"),
                        resultSet.getInt("id_payment_form"),
                        resultSet.getInt("id_trip_booking")
                    );
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return payments;
    }

}