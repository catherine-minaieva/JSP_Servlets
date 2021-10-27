package ua.goit.repositoty;

import lombok.SneakyThrows;
import ua.goit.model.Skill;
import ua.goit.config.DbConnection;
import ua.goit.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SkillRepositoryImpl implements BaseRepository<Skill, Long> {

    private final Connection CONNECTION = DbConnection.getInstance().getConnection();
    private final String SCHEMA_NAME = PropertiesLoader.getProperty("db.schemaName");

    @SneakyThrows
    @Override
    public Collection<Skill> findAll() {
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT * FROM skills");
        final List<Skill> skillArrayList = new ArrayList<>();
        while (resultSet.next()) {final Skill skill = Skill.builder()
                .id(resultSet.getLong("id"))
                .language(resultSet.getString("language"))
                .level(resultSet.getString("level"))
                .build();
            skillArrayList.add(skill);
        }
        return skillArrayList;
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        CONNECTION.createStatement().execute("DELETE FROM " + SCHEMA_NAME + ".skills WHERE id=" + id);
    }

    @SneakyThrows
    @Override
    public Skill findById(Long id) {
        Skill skill = new Skill();
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT * FROM skills WHERE id=" + id + ";");
        while (resultSet.next()) {skill = Skill.builder()
                .id(resultSet.getLong("id"))
                .language(resultSet.getString("language"))
                .level(resultSet.getString("level"))
                .build();
        }
        return skill;
    }

    @SneakyThrows
    @Override
    public Skill create(Skill skill) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO " + SCHEMA_NAME + ".skills (language ,level) VALUES (?,?)");
        return getSkill(skill, preparedStatement);
    }

    @SneakyThrows
    @Override
    public Skill update(Long id, Skill skill) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("UPDATE " + SCHEMA_NAME + ".skills set language =?,level=? WHERE id=" + id + ";");
        return getSkill(skill, preparedStatement);
    }

    private Skill getSkill(Skill skill, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, skill.getLanguage());
        preparedStatement.setString(2, skill.getLevel());
        preparedStatement.executeUpdate();
        return skill;
    }
}
