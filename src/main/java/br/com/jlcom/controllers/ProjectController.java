package br.com.jlcom.controllers;

import br.com.jlcom.models.Project;
import br.com.jlcom.models.Task;
import br.com.jlcom.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    public void save (Project project) {
        String sql = "INSERT INTO projects ("
                + "name, "
                + "description, "
                + "created_date, "
                + "updated_date) "
                + "VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreatedDate().getTime()));
            statement.setDate(4, new java.sql.Date(project.getUpdatedDate().getTime()));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar projeto", e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public void update (Project project) {
        String sql = "UPDATE projects SET " +
                "name = ?, " +
                "description = ?, " +
                "createdDate = ?, " +
                "updatedDate = ?, " +
                "WHERE id_projeto = ?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreatedDate().getTime()));
            statement.setDate(4, new java.sql.Date(project.getUpdatedDate().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar  projeto", e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public void delete (int idProject) throws SQLException {
        String sql = "DELETE FROM projects WHERE id_projeto = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar projeto", e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    public List<Project> getAllProjects () throws SQLException {
        String sql = "SELECT * FROM projects";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Project> projects = new ArrayList<Project>();

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id_projeto"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedDate(resultSet.getDate("created_date"));
                project.setUpdatedDate(resultSet.getDate("updated_date"));
                projects.add(project);
            }
        } catch (Exception e) {
            throw new SQLException("Erro ao buscar todas os projetos", e);

        }finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);

        }

        return projects;
    }
}
