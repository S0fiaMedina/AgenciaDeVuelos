package com.agenciavuelos.modules.plane.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;

public class PlaneMySQLRepository implements PlaneRepository{
    private final String url;
    private final String user;
    private final String password;

    public PlaneMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    @Override
    public void save(Plane plane) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                    INSERT INTO plane (plates, capacity, fabrication_date, id_status, id_model, id_airline)
                    VALUES (?, ? , ?, ?, ?, ?)
                    """;
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, plane.getPlates());
                statement.setInt(2, plane.getCapacity());
                statement.setString(3, plane.getFabricationDate());
                statement.setInt(4, plane.getIdStatus());
                statement.setInt(5, plane.getIdModel());


                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override
    public void update(Plane plane) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Plane plane) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Plane> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Plane> findById(int id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public int verifyPlate(String plate) {
        int numberOfCoincidences = 0;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                    SELECT COUNT(id) FROM plane
                    WHERE plates = ?
                    """;
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, plate);
                statement.executeUpdate();

                // Almacena el numero de coincidencias en una variable 
                ResultSet rs = statement.executeQuery();
                if(rs.next()) {
                    numberOfCoincidences= rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return numberOfCoincidences;
    }

}