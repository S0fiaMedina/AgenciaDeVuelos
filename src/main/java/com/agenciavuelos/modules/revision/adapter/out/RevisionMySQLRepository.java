package com.agenciavuelos.modules.revision.adapter.out;


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
import com.agenciavuelos.modules.revision.domain.Revision;
import com.agenciavuelos.modules.revision.infrastructure.RevisionRepository;

public class RevisionMySQLRepository implements RevisionRepository{
    private final String url;
    private final String user;
    private final String password;

    public RevisionMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public int save(Revision revision) {
        // ingreso de daatos a la tabla de revision
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO revision (revision_date, plane_plates, description) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, revision.getRevisionDate());
                statement.setString(2, revision.getPlanePlate());
                statement.setString(3, revision.getDescription());
                statement.executeUpdate();
                Util.showSuccess("Revision registrada");

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id =  generatedKeys.getInt(1); // Devuelve el ID generado
                    return id;
                }
                
            }

        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return 0;
    }

    @Override
    public void update(Revision revision) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE revision SET revision_date = ?, plane_plates = ?, description = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                
                statement.setString(1, revision.getRevisionDate());
                statement.setString(2, revision.getPlanePlate());
                statement.setString(3, revision.getDescription());
                statement.setInt(4, revision.getId());
                
                statement.executeUpdate();
                Util.showSuccess("Revision actualizada.");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM revision WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                Util.showSuccess("Revision eliminada");
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
    }

    @Override
    public List<Revision> findAllByPlane(String platePlane) {
        List<Revision> revisions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                    SELECT r.id, r.revision_date, r.description, r.plane_plates FROM revision AS r
                    WHERE r.plane_plates = ?
                    """;
                    // INNER JOIN rev_employee AS re ON re.id_revision = r.id
            try (PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setString(1, platePlane);
                    ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Revision revision = new Revision();
                    revision.setRevisionDate( resultSet.getString("r.revision_date") );
                    revision.setPlanePlate( resultSet.getString("r.plane_plates") );
                    revision.setDescription( resultSet.getString("r.description") );
                    revision.setId( resultSet.getInt("r.id") );

                    revisions.add(revision);
                }
            }
        } catch (SQLException e) {
        System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return revisions;
    }

    @Override
    public Optional<Revision> findByid(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = """
                    SELECT r.id, r.revision_date, r.description, r.plane_plates FROM revision AS r
                    WHERE r.id = ?
                    """;
                    // INNER JOIN rev_employee AS re ON re.id_revision = r.id
            try (PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, id);
                    ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Revision revision = new Revision();
                    revision.setRevisionDate( resultSet.getString("r.revision_date") );
                    revision.setPlanePlate( resultSet.getString("r.plane_plates") );
                    revision.setDescription( resultSet.getString("r.description") );
                    revision.setId( resultSet.getInt("r.id") );

                    return Optional.of(revision);
                }
            }
        } catch (SQLException e) {
        System.out.println("Se ha producido un error :(. Motivo: \n" + e.getMessage());
        }
        return Optional.empty();
    }
}