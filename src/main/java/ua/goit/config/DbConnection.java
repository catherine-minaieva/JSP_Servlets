package ua.goit.config;

import lombok.SneakyThrows;
import ua.goit.util.PropertiesLoader;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection implements Closeable {

    private static final String URL = PropertiesLoader.getProperty("URL");
    private static final String username = PropertiesLoader.getProperty("username");
    private static final String password = PropertiesLoader.getProperty("password");
    private static final String JDBC_DRIVER = PropertiesLoader.getProperty("JDBC_DRIVER");

    private static DbConnection databaseConnection;
    private Connection connection;

    @SneakyThrows
    public DbConnection() {
        Class.forName(JDBC_DRIVER);
        this.connection = DriverManager.getConnection(URL, username, password);
    }

    @SneakyThrows
    public static DbConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DbConnection();
        } else if (databaseConnection.getConnection().isClosed()) {
            databaseConnection = new DbConnection();
        }
        return databaseConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    @SneakyThrows
    @Override
    public void close() {
        connection.close();
    }
}

