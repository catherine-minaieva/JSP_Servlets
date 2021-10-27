package ua.goit.repositoty;

import lombok.SneakyThrows;
import ua.goit.model.Developer;
import ua.goit.config.DbConnection;
import ua.goit.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryRepositotyImpl implements ua.goit.repositoty.QueryRepository {

    private final Connection CONNECTION = DbConnection.getInstance().getConnection();
    private final String SCHEMA_NAME = PropertiesLoader.getProperty("db.schemaName");

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersOfProject(Long projectId) {
        List<Developer> developersList = new ArrayList<>();
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM " + SCHEMA_NAME + ".developers_projects "
                + "inner join " + SCHEMA_NAME + ".developers on " + SCHEMA_NAME + ".developers_projects.developer_id = " + SCHEMA_NAME + ".developers.id "
                + "inner join " + SCHEMA_NAME + ".projects on " + SCHEMA_NAME + ".developers_projects.project_id = " + SCHEMA_NAME + ".projects.id "
                + "WHERE " + SCHEMA_NAME + ".projects.id=?");
        preparedStatement.setLong(1, projectId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Developer developer = buildDeveloper(resultSet);
            developersList.add(developer);
        }
        return developersList;
    }

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersByLanguage(String language) {
        String getAllDevelopersByLanguage =
                """
                        SELECT d.id, d.name, d.gender, d.age, d.salary
                        FROM developers d
                        JOIN developers_skills ds ON d.id = ds.developer_id
                        JOIN skills s ON ds.skill_id = s.id
                        WHERE s.language = ?GROUP BY d.id;""";
        return getDevelopers(language, getAllDevelopersByLanguage);
    }

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersByLevel(String level) {
        String developersByLevel =
                """
                        SELECT d.id, d.name, d.gender, d.age, d.salary
                        FROM developers d
                        JOIN developers_skills ds ON d.id = ds.developer_id
                        JOIN skills s ON ds.skill_id = s.id
                        WHERE s.level = ?GROUP BY d.id;""";
        return getDevelopers(level, developersByLevel);
    }

    private List<Developer> getDevelopers(String level, String developersByLevel) throws SQLException {
        List<Developer> developersList = new ArrayList<>();

        PreparedStatement statement = CONNECTION.prepareStatement(developersByLevel);
        statement.setString(1, level);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Developer developer = buildDeveloper(resultSet);
            developersList.add(developer);
        }
        return developersList;
    }

    private Developer buildDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer;
        developer = Developer.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .age(resultSet.getInt("age"))
                .gender(resultSet.getString("gender"))
                .salary(resultSet.getDouble("salary"))
                .build();
        return developer;
    }

    @SneakyThrows
    public String getSumOfSalariesForProject(Long id) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(
                "SELECT projects.name, sum(salary) as salary " +
                        " FROM " + SCHEMA_NAME + ".developers " +
                        " INNER JOIN " + SCHEMA_NAME + ".developers_projects " +
                        " ON developers_projects.developer_id = developers.id " +
                        " INNER JOIN " + SCHEMA_NAME + ".projects " +
                        " ON developers_projects.project_id = projects.id " +
                        " WHERE projects.id=?" +
                        " GROUP BY projects.name");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return String.format("Salary of the project %s - %s",
                    resultSet.getString("projects.name"),
                    resultSet.getString("salary"));
        }
        return null;
    }

    @SneakyThrows
    @Override
    public String getProjectsWithDate() {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT" +
                " creation_date," +
                " projects.name," +
                " COUNT(developers.name) AS devs_quantity" +
                " FROM " + SCHEMA_NAME + ".projects" +
                " INNER JOIN " + SCHEMA_NAME + ".developers_projects" +
                " ON developers_projects.project_id = projects.id" +
                " INNER JOIN " + SCHEMA_NAME + ".developers" +
                " ON developers_projects.developer_id = developers.id" +
                " GROUP BY projects.name,creation_date"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        String projects = "";
        while (resultSet.next()) {
            projects = String.join("",
                    projects,
                    String.join(" - ",
                            resultSet.getString("creation_date"),
                            resultSet.getString("projects.name"),
                            resultSet.getString("devs_quantity")
                    ));
        }
        return projects;
    }
}
