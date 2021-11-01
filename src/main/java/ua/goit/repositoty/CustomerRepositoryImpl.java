package ua.goit.repositoty;

import lombok.SneakyThrows;
import ua.goit.model.Customer;
import ua.goit.config.DbConnection;
import ua.goit.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerRepositoryImpl implements BaseRepository<Customer, Long> {

    private final Connection CONNECTION = DbConnection.getInstance().getConnection();
    private final String SCHEMA_NAME = PropertiesLoader.getProperty("db.schemaName");


    @SneakyThrows
    @Override
    public Collection<Customer> findAll() {
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT*FROM customers");
        final List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            final Customer customer = Customer.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .taxCode(resultSet.getString("tax_code"))
                    .headOffice(resultSet.getString("head_office"))
                    .build();
            customerList.add(customer);
        }
        return customerList;
    }

    @SneakyThrows
    @Override
    public Customer findById(Long id) {
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT * FROM customers WHERE id=" + id + ";");
        Customer customer = new Customer();
        while (resultSet.next()) {
            customer = Customer.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .taxCode(resultSet.getString("tax_code"))
                    .headOffice(resultSet.getString("head_office"))
                    .build();
        }
        return customer;
    }

    @SneakyThrows
    @Override
    public Customer create(Customer customer) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO customers(id, name, tax_code, head_office) VALUES (?, ?, ?, ?)");
        return getCustomer(customer, preparedStatement);
    }

    @SneakyThrows
    @Override
    public Customer update(Long id, Customer customer) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("UPDATE customers SET id = ?, name=?, tax_code=?, head_office=? WHERE id=" + id + ";");
        return getCustomer(customer, preparedStatement);
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        CONNECTION.createStatement().execute("DELETE FROM " + SCHEMA_NAME + ".customers WHERE id=" + id);
    }

    private Customer getCustomer(Customer customer, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getTaxCode());
        preparedStatement.setString(4, customer.getHeadOffice());
        preparedStatement.executeUpdate();
        return customer;
    }
}

