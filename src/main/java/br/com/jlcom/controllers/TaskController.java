package br.com.jlcom.controllers;

import br.com.jlcom.models.Task;
import br.com.jlcom.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskController {

    public void save (Task task){
        String sql = "INSERT INTO tasks ("
                + "id_project, "
                + "name, "
                + "description, "
                + "status, "
                + "notes, "
                + "dead_line, "
                + "created_date, "
                + "updated_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getStatus());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new java.sql.Date(task.getDeadLine().getTime()));
            statement.setDate(7, new java.sql.Date(task.getCreatedDate().getTime()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedDate().getTime()));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar tarefa", e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }
    public void update (Task task) {

        String sql = "UPDATE tasks SET " +
                "id_project = ?, " +
                "name = ?, " +
                "description = ?, " +
                "notes = ?, " +
                "status = ?, " +
                "dead_line = ?, " +
                "created_date = ?, " +
                "updated_date = ? " +
                "WHERE id_tasks = ?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.getStatus());
            statement.setDate(6, new java.sql.Date(task.getDeadLine().getTime()));
            statement.setDate(7, new java.sql.Date(task.getCreatedDate().getTime()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedDate().getTime()));
            statement.setInt(9, task.getIdTask());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar  tarefa" , e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }
    public void deleteById (int taskId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id_tasks = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa", e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }

    public List<Task> getAllTasks (int projectId) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id_project = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Task> tasks = new ArrayList<Task>();

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, projectId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setIdTask(resultSet.getInt("id_tasks"));
                task.setIdProject(resultSet.getInt("id_project"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setStatus(resultSet.getBoolean("status"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadLine(resultSet.getDate("dead_line"));
                task.setCreatedDate(resultSet.getDate("created_date"));
                task.setUpdatedDate(resultSet.getDate("updated_date"));
                tasks.add(task);
            }
        } catch (Exception e) {
            throw new SQLException("Erro ao buscar todas as tarefas", e);

        }finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);

        }

        return tasks;
    }

}
