package ua.goit.repositoty;

import lombok.SneakyThrows;
import ua.goit.model.Company;
import ua.goit.config.DbConnection;
import ua.goit.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompanyRepositoryImpl implements BaseRepository<Company, Long> {

    private final Connection CONNECTION = DbConnection.getInstance().getConnection();
    private final String SCHEMA_NAME = PropertiesLoader.getProperty("db.schemaName");


    @SneakyThrows
    @Override
    public Collection<Company> findAll() {
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT*FROM companies");
        final List<Company> companyList = new ArrayList<>();
        while (resultSet.next()) {
            final Company company = Company.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .headOffice(resultSet.getString("head_office"))
                    .build();
            companyList.add(company);
        }
        return companyList;
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        CONNECTION
                .createStatement()
                .execute("DELETE FROM " + SCHEMA_NAME + ".companies" +
                         " WHERE id=" + id);
    }

    @SneakyThrows
    @Override
    public Company findById(Long id) {
        Company company = new Company();
        final ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT * FROM companies WHERE id=" + id + ";");
        while (resultSet.next()) {
            company = Company.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .headOffice(resultSet.getString("head_office"))
                    .build();
        }
        return company;
    }

    @SneakyThrows
    @Override
    public Company create(Company company) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO " + SCHEMA_NAME + ".companies (id, name,head_office) VALUES (?,?,?) ;");
        preparedStatement.setLong(1, company.getID());
        preparedStatement.setString(2, company.getName());
        preparedStatement.setString(3, company.getHeadOffice());
        preparedStatement.executeUpdate();
        return company;
    }

    @SneakyThrows
    @Override
    public Company update(Long id, Company company) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("UPDATE  " + SCHEMA_NAME + ".companies SET name=?,head_office=?  WHERE id=" + id + ";");
        preparedStatement.setString(1, company.getName());
        preparedStatement.setString(2, company.getHeadOffice());
        preparedStatement.execute();
        return company;
    }

}
