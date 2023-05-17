package data;
import java.sql.Connection;
import java.sql.*;
public class SingletonConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/barasserie";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
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
