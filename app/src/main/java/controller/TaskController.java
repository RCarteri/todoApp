package controller;

import model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.ConnectionFactory.closeConnection;
import static util.ConnectionFactory.getConnection;

public class TaskController {
    public void save(Task task) {
        String sql = "INSERT INTO tasks (idProject, " +
                "name, " +
                "description, " +
                "completed, " +
                "notes, " +
                "deadline, " +
                "createAt, " +
                "updateAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreateAt().getTime()));
            statement.setDate(8, new Date(task.getUpdateAt().getTime()));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar a tarefa " +
                    e.getMessage(), e);
        } finally {
            closeConnection(conn, statement);
        }
    }

    public void update(Task task) throws SQLException {
        String sql = "UPDATE tasks SET " +
                "idProject = ?, " +
                "name = ?, " +
                "description = ?, " +
                "notes = ?, " +
                "deadline = ?, " +
                "completed = ?, " +
                "createAt = ?, " +
                "updateAt = ?" +
                "WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreateAt().getTime()));
            statement.setDate(8, new Date(task.getUpdateAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar a tarefa");
        }
    }

    public void remove(int taskId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa");
        } finally {
            closeConnection(conn, statement);
        }
    }

    public List<Task> getAll(int idProject) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<Task>();
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProjeto(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreateAt(resultSet.getDate("createAt"));
                task.setUpdateAt(resultSet.getDate("updateAt"));
                tasks.add(task);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao retornar a tarefa " + e.getMessage(), e);
        } finally {
            closeConnection(conn, statement, resultSet);
        }
        return tasks;
    }
}
