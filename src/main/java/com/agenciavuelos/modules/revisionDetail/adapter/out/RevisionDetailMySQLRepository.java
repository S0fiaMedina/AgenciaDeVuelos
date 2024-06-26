package com.agenciavuelos.modules.revisionDetail.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.agenciavuelos.modules.country.domain.Country;
import com.agenciavuelos.modules.revisionDetail.domain.RevisionDetail;
import com.agenciavuelos.modules.revisionDetail.infrastructure.RevisionDetailRepository;

public class RevisionDetailMySQLRepository implements RevisionDetailRepository{
    private final String url;
    private final String user;
    private final String password;

    public RevisionDetailMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(RevisionDetail revisionDetail) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO rev_employee (id_employee, id_revision) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, revisionDetail.getEmployeeId());
                statement.setInt(2, revisionDetail.getRevisionId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(RevisionDetail revisionDetail) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE rev_employee SET id_employee = ? WHERE id_revision = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, revisionDetail.getEmployeeId());
                statement.setInt(2, revisionDetail.getRevisionId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idRevision) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM rev_employee WHERE id_revision = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idRevision);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

}