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
import java.util.Collection;
import java.util.List;

public class DeveloperRepositoryImpl implements BaseRepository<Developer, Long> {

    private final Connection CONNECTION = DbConnection.getInstance().getConnection();
    private final String SCHEMA_NAME = PropertiesLoader.getProperty("db.schemaName");

    @SneakyThrows
    @Override
    public Collection<Developer> findAll() {
        final List<Developer> developersList = new ArrayList<>();
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT * FROM developers");
        while (resultSet.next()) {
            Developer developer = buildDeveloper(resultSet);
            developersList.add(developer);
        }
        return developersList;
    }

    @SneakyThrows
    @Override
    public Developer findById(Long id) {
        Developer developer = new Developer();
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM " + SCHEMA_NAME + ".developers WHERE id=" + id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            developer = buildDeveloper(resultSet);
        }
        return developer;
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
    @Override
    public Developer create(Developer developer) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO " + SCHEMA_NAME + ".developers (id, name,age,gender,salary)" + " VALUES (?,?,?,?,?)");
        return updateDeveloper(developer, preparedStatement);
    }

        @SneakyThrows
    @Override
    public Developer update(Long id, Developer developer) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("UPDATE " + SCHEMA_NAME + ".developers set id=?, name=?, age=?, gender=?, salary=? WHERE id=" + id + ";");
        return updateDeveloper(developer, preparedStatement);
    }

    private Developer updateDeveloper(Developer developer, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, developer.getId());
        preparedStatement.setString(2, developer.getName());
        preparedStatement.setInt(3, developer.getAge());
        preparedStatement.setString(4, developer.getGender());
        preparedStatement.setDouble(5, developer.getSalary());
        preparedStatement.executeUpdate();
        return developer;
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        CONNECTION.prepareStatement("DELETE FROM " + SCHEMA_NAME + ".developers WHERE id=" + id).executeUpdate();
    }
}
