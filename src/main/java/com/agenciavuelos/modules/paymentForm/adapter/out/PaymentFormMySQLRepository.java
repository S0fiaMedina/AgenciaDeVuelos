package com.agenciavuelos.modules.paymentForm.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.paymentForm.domain.PaymentForm;
import com.agenciavuelos.modules.paymentForm.infrastructure.PaymentFormRepository;

public class PaymentFormMySQLRepository implements PaymentFormRepository {
    private final String url;
    private final String user;
    private final String password;

    public PaymentFormMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(PaymentForm paymentForm) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO payment_form (description) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, paymentForm.getDescription());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PaymentForm paymentForm) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE payment_form SET description = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, paymentForm.getDescription());
                statement.setInt(2, paymentForm.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PaymentForm> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM payment_form WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        PaymentForm paymentForm = new PaymentForm(
                            resultSet.getInt("id"),
                            resultSet.getString("description")
                        );
                        return Optional.of(paymentForm);
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
            String query = "DELETE FROM payment_form WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PaymentForm> findAll() {
        List<PaymentForm> paymentForms = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM payment_form";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PaymentForm paymentForm = new PaymentForm(
                        resultSet.getInt("id"),
                        resultSet.getString("description")
                    );
                    paymentForms.add(paymentForm);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return paymentForms;
    }
}