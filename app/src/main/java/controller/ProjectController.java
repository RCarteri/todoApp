package controller;

import model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static util.ConnectionFactory.closeConnection;
import static util.ConnectionFactory.getConnection;

public class ProjectController {
    public void save(Project project) {
        String sql = "INSERT INTO projects (" +
                "name, " +
                "description, " +
                "createAt, " +
                "updateAt) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreateAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o projeto " +
                    e.getMessage(), e);
        } finally {
            closeConnection(conn, statement);
        }
    }

    public void update(Project project) throws SQLException {
        String sql = "UPDATE projects SET " +
                "name = ?, " +
                "description = ?, " +
                "createAt = ?, " +
                "updateAt = ?" +
                "WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreateAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o projeto", e);
        }
    }

    public void remove(int projectId) throws SQLException {
        String sql = "DELETE FROM projects WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar o projeto");
        } finally {
            closeConnection(conn, statement);
        }
    }

    public List<Project> getAll() {
        String sql = "SELECT * FROM projects";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Project> projects = new ArrayList<Project>();
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreateAt(resultSet.getDate("createAt"));
                project.setUpdateAt(resultSet.getDate("updateAt"));
                projects.add(project);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao retornar o projeto " + e.getMessage(), e);
        } finally {
            closeConnection(conn, statement, resultSet);
        }
        return projects;
    }
}
