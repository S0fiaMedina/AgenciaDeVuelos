package com.agenciavuelos.modules.tripulationRole.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripulationRole.domain.TripulationRole;
import com.agenciavuelos.modules.tripulationRole.infrastructure.TripulationRoleRepository;

public class TripulationRoleMySQLRepository implements TripulationRoleRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripulationRoleMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(TripulationRole tripulationRole) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO tripulation_roles (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripulationRole.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TripulationRole tripulationRole) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE tripulation_roles SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripulationRole.getName());
                statement.setInt(2, tripulationRole.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<TripulationRole> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripulation_roles WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripulationRole tripulationRole = new TripulationRole(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(tripulationRole);
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
            String query = "DELETE FROM tripulation_roles WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TripulationRole> findAll() {
        List<TripulationRole> tripulationRoles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripulation_roles";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripulationRole tripulationRole = new TripulationRole(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    tripulationRoles.add(tripulationRole);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return tripulationRoles;
    }
}