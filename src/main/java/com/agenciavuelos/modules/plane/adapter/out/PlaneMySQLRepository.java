package com.agenciavuelos.modules.plane.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
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
                statement.setInt(6, plane.getIdAirline());


                statement.executeUpdate();
                Util.showSuccess("Avion registrado");
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override
    public void update(Plane plane) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                UPDATE plane SET plates = ?, capacity = ?, fabrication_date = ?, id_status = ?, id_model = ?, id_airline = ?
                WHERE id = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, plane.getPlates());
                statement.setInt(2, plane.getCapacity());
                statement.setString(3, plane.getFabricationDate());
                statement.setInt(4, plane.getIdStatus());
                statement.setInt(5, plane.getIdModel());
                statement.setInt(6, plane.getIdAirline());
                statement.setInt(7, plane.getId());

                statement.executeUpdate();
                Util.showSuccess("Avion actualizado. ");
                
            }
        }  catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        
    }

    @Override
    public void delete(String plate) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                    DELETE FROM plane WHERE plates = ?
                    """;
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, plate);


                statement.executeUpdate();
                Util.showSuccess("Avion eliminado correctamente");
            }

        } catch (SQLIntegrityConstraintViolationException e){
            Util.showWarning("No se puede eliminar ya que tiene informacion asociada.  ");
        
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        
    }

    @Override
    public List<Plane> findAll() {
        List<Plane> planes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM plane";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Plane plane = new Plane();
                    plane.setId(  resultSet.getInt("id") );
                    plane.setPlates( resultSet.getString("plates") );
                    plane.setCapacity(  resultSet.getInt("capacity") );  
                    plane.setIdStatus( resultSet.getInt("id_status") );
                    plane.setIdModel( resultSet.getInt("id_model") );
                    plane.setIdAirline( resultSet.getInt("id_airline") );


                  
                       
                    planes.add(plane);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return planes;
    }


    @Override
    public Optional<Plane> findById(String plate) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT p.id, p.plates, p.capacity, p.fabrication_date, s.name, m.name, a.name FROM plane AS p
                INNER JOIN airline AS a ON a.id = p.id_airline
                INNER JOIN status AS s ON s.id = p.id_status
                INNER JOIN model AS m ON m.id = p.id_model 
                WHERE p.plates = ?
                """;

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, plate);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Plane plane = new Plane();
                        plane.setId(  resultSet.getInt("id") );
                        plane.setPlates( resultSet.getString("p.plates") );
                        plane.setCapacity(  resultSet.getInt("p.capacity") );  
                        plane.setStatusStr( resultSet.getString("s.name") );
                        plane.setModelStr(resultSet.getString("m.name") );
                        plane.setAirlineStr( resultSet.getString("a.name") );
                        return Optional.of(plane);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
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


    @Override
    public Optional<Plane> findByTrip(int idTrip) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT p.plates, p.capacity
                FROM plane p
                INNER JOIN flight_connection fc
                ON fc.plane_plates = p.plates
                INNER JOIN trip t
                ON t.id = fc.id_trip
                WHERE t.departure_airport_id = fc.id_airport
                AND t.id = ?;
                """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idTrip);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Plane plane = new Plane();
                        plane.setPlates( resultSet.getString("p.plates") );
                        plane.setCapacity(  resultSet.getInt("p.capacity") );  
                        return Optional.of(plane);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return Optional.empty();
    }

}