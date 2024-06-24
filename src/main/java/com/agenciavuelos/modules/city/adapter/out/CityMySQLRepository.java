package com.agenciavuelos.modules.city.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.city.domain.City;
import com.agenciavuelos.modules.city.infrastructure.CityRepository;

public class CityMySQLRepository implements CityRepository {
    private final String url;
    private final String user;
    private final String password;

    public CityMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(City city) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO city (name, id_country) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, city.getName());
                statement.setInt(2, city.getIdCountry());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(City city) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE city SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, city.getName());
                statement.setInt(2, city.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<City> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM city WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        City city = new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("id_country")
                        );
                        return Optional.of(city);
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
            String query = "DELETE FROM city WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM city";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    City city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("id_country")
                    );
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return cities;
    }
    
}