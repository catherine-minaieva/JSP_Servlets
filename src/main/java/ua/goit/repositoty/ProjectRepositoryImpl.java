package ua.goit.repositoty;

import lombok.SneakyThrows;
import ua.goit.model.Project;
import ua.goit.config.DbConnection;
import ua.goit.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectRepositoryImpl implements BaseRepository<Project, Long> {

    private final Connection CONNECTION = DbConnection.getInstance().getConnection();
    private final String SCHEMA_NAME = PropertiesLoader.getProperty("db.schemaName");

    @SneakyThrows
    @Override
    public Collection<Project> findAll() {
        final List<Project> projectList = new ArrayList<>();
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT * FROM projects");
        while (resultSet.next()) {
            final Project project = buildProject(resultSet);
            projectList.add(project);
        }
        return projectList;
    }

    @SneakyThrows
    @Override
    public Project findById(Long id) {

        Project project = new Project();
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT * FROM projects WHERE id=" + id + ";");
        while (resultSet.next()) {
            project = buildProject(resultSet);
        }
        return project;
    }

    private Project buildProject(ResultSet resultSet) throws SQLException {
        final Project project = Project.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .baseTechnology(resultSet.getString("base_technology"))
                .creationDate(resultSet.getString("creation_date"))
                .cost(resultSet.getLong("cost"))
                .build();
        return project;
    }

    @SneakyThrows
    @Override
    public Project create(Project project) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO " + SCHEMA_NAME + ".projects (id, name, base_technology, creation_date, cost)" + " VALUES (?,?,?,?,?)");
        return getProject(project, preparedStatement);
    }

    @SneakyThrows
    @Override
    public Project update(Long id, Project project) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("UPDATE " + SCHEMA_NAME + ".projects set id =?, name=?,base_technology=?, creation_date=?, cost=?  WHERE id=" + id + ";");
        return getProject(project, preparedStatement);
    }

    private Project getProject(Project project, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, project.getId());
        preparedStatement.setString(2, project.getName());
        preparedStatement.setString(3, project.getBaseTechnology());
        preparedStatement.setString(4, project.getCreationDate());
        preparedStatement.setLong(5, project.getCost());
        preparedStatement.executeUpdate();
        return project;
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        CONNECTION.createStatement().execute("DELETE FROM " + SCHEMA_NAME + ".projects WHERE id=" + id);
    }
}
