package com.agenciavuelos.modules.flightConnection.adapter.out;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.flightConnection.domain.FlightConnection;
import com.agenciavuelos.modules.flightConnection.infrastructure.FlightConnectionRepository;



public class FlightConnectionMySQLRepository implements FlightConnectionRepository{

    private final String url;
    private final String user;
    private final String password;

    public FlightConnectionMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(FlightConnection flightConnection) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                    INSERT INTO flight_connection (connection_number, id_trip, plane_plates, id_airport)
                    VALUES (?, ?, ?, ?)
                    """;
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightConnection.getConnectionNumber());
                statement.setInt(2, flightConnection.getIdTrip());
                statement.setString(3, flightConnection.getPlanePlates());
                statement.setString(4, flightConnection.getIdAirport());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        
    }

    @Override
    public void update(FlightConnection flightConnection) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                UPDATE flight_connection SET connection_number = ?, plane_plates = ?, id_airport = ?
                WHERE id = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightConnection.getConnectionNumber() );
                statement.setString(2, flightConnection.getPlanePlates() );
                statement.setString(3, flightConnection.getIdAirport() );
                statement.setInt(4, flightConnection.getId());

                statement.executeUpdate();
            
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    

    @Override
    public void setPlaneToTrip(int idTrip, String planePlate) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                UPDATE flight_connection SET  plane_plates = ?
                WHERE id = ?
                """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, planePlate );
                statement.setInt(2, idTrip );
                

                statement.executeUpdate();
            
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        
    }

    @Override
    public List<FlightConnection> findAllByTrip(int idTrip) {
        List<FlightConnection> flightConnections = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String query = """
                    SELECT fc.id, fc.connection_number, fc.plane_plates, fc.id_airport,  fc.id_trip 
                    FROM flight_connection AS fc
                    INNER JOIN trip AS t ON t.id = fc.id_trip 
                    WHERE fc.id_trip = ?;
                    """;

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idTrip);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {

                    FlightConnection flightConnection = new FlightConnection();
                    flightConnection.setId( resultSet.getInt("fc.id") );
                    flightConnection.setConnectionNumber( resultSet.getString("fc.connection_number") );
                    flightConnection.setPlanePlates(  resultSet.getString("fc.plane_plates") );  
                    flightConnection.setIdAirport( resultSet.getString("fc.id_airport") );
                    flightConnection.setIdTrip( resultSet.getInt("fc.id_trip") );


                    flightConnections.add(flightConnection);
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return flightConnections;
    }

    @Override
    public Optional<FlightConnection> findById(int id) {

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String query = """
                    SELECT fc.id, fc.connection_number, fc.plane_plates, fc.id_airport,  fc.id_trip 
                    FROM flight_connection AS fc
                    INNER JOIN trip AS t ON t.id = fc.id_trip 
                    WHERE fc.id = ?
                    """;

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {

                    FlightConnection flightConnection = new FlightConnection();
                    flightConnection.setId( resultSet.getInt("fc.id") );
                    flightConnection.setConnectionNumber( resultSet.getString("fc.connection_number") );
                    flightConnection.setPlanePlates(  resultSet.getString("fc.plane_plates") );  
                    flightConnection.setIdAirport( resultSet.getString("fc.id_airport") );
                    flightConnection.setIdTrip( resultSet.getInt("fc.id_trip") );


                    return Optional.of(flightConnection);
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
            String query = """
                    DELETE FROM flight_connection WHERE id = ?
                    """;
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);


                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        
    }


}