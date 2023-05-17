package data;

import java.sql.*;
import java.util.Properties;

public class SingletonConnection {
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            Properties properties = DBConfiguration.getInstance();
            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
        }
        return connection;
    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }
}