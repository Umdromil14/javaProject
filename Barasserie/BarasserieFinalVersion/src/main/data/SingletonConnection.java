package main.data;

import java.sql.*;
import java.util.Properties;

import main.exception.DataAccessException;

public class SingletonConnection {
    private static Connection connection;

    public static Connection getInstance() throws DataAccessException {
        if (connection == null) {
            try {
                Properties properties = DBConfiguration.getInstance();
                connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
            } catch (SQLException e) {
                throw new DataAccessException("Error connecting to the database");
            }
            
        }
        return connection;
    }

    public static void close() throws DataAccessException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataAccessException("Error closing the connection");
            }
        }
        connection = null;
    }
}