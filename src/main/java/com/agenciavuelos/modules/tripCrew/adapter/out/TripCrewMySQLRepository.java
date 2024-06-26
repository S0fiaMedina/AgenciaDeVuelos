package com.agenciavuelos.modules.tripCrew.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.agenciavuelos.modules.tripCrew.domain.TripCrew;
import com.agenciavuelos.modules.tripCrew.infrastructure.TripCrewRepository;

public class TripCrewMySQLRepository implements TripCrewRepository{

    private final String url;
    private final String user;
    private final String password;

    public TripCrewMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void addTripulationToTrip(TripCrew tripCrew) {

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_crew (id_employees, id_trip) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, tripCrew.getIdEmployee());
                statement.setInt(2, tripCrew.getIdTrip());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        
    }


    @Override
    public List<TripCrew> getTripulationFromTrip(int idTrip) {
         List<TripCrew> tripCrews = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                SELECT e.id, e.name AS employee_name, tr.name AS role_name
                FROM employee AS e
                INNER JOIN tripulation_roles AS tr ON tr.id = e.id_rol
                INNER JOIN trip_crew AS tc ON tc.id_employees = e.id
                INNER JOIN trip AS t ON t.id = tc.id_trip WHERE t.id = ?;
                    """;
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idTrip);
                ResultSet resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    TripCrew tripCrew = new TripCrew(
                        resultSet.getString("e.id"),
                        resultSet.getInt(idTrip),
                        resultSet.getString("tr.name"),
                        resultSet.getString("e.name")
                    );
                    

                    tripCrews.add(tripCrew);
                }
            }
        } catch (SQLException e) {
        System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return tripCrews;
    }
        
        
}
