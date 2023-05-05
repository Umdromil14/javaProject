package data;

import java.sql.*;

import tools.*;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/barasserie";
    private static final String USER = "root";
    private static final String PASSWORD = "bidondon";

    private static final String STARTING_STATUS = "regular";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
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

    public static boolean userExist(Integer id) throws SQLException {
        String query = "SELECT id FROM business_entity WHERE id = ?";

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        rs = Utils.checkingResultSet(rs);
        return rs != null;
    }

    public static int addAddress(Address address) throws SQLException {
        String query = "SELECT id FROM city WHERE name = ? AND postalCode = ? AND country = ?";

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, address.getCity());
        statement.setInt(2, address.getPostalCode());
        statement.setString(3, address.getCountry());

        ResultSet rs = statement.executeQuery();
        rs = Utils.checkingResultSet(rs);
        if (rs == null) {
            throw new SQLException("Creating address failed, city not found.");
        }
        int cityId = rs.getInt("id");

        query = "INSERT INTO address (street, number, city) VALUES (?, ?, ?)";
        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, address.getStreet());
        statement.setInt(2, address.getNumber());
        statement.setInt(3, cityId);

        int affectedRow = statement.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("Creating address failed, no rows affected.");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();
        int addressId;
        if (generatedKeys.next()) {
            addressId =  generatedKeys.getInt(1);
        } else {
            throw new SQLException("Creating address failed, no ID obtained.");
        }

        return addressId;
    }

    public static void addEmail(int id, String email) throws SQLException {
        String query = "INSERT INTO communication (entity, type, communicationDetails) VALUES (?, ?, ?)";

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, id);
        statement.setString(2, "email");
        statement.setString(3, email);

        statement.executeUpdate();
    }

    public static void addUser(String firstname, String lastname, String email, String password, Address address) throws SQLException {
        int addressId = addAddress(address);

        String query = "INSERT INTO business_entity " +
        "(address, tier, lastname, firstname, isClient, isSupplier, registrationDate, hashedPassword, salt) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, addressId);
        statement.setString(2, STARTING_STATUS);
        statement.setString(3, lastname);
        statement.setString(4, firstname);
        statement.setBoolean(5, true);
        statement.setBoolean(6, false);
        statement.setDate(7, new Date(System.currentTimeMillis()));

        String salt = Utils.generateSalt();
        String hashedPassword = Utils.hashPassword(password, salt);
        statement.setString(8, hashedPassword);
        statement.setString(9, salt);

        int affectedRow = statement.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            addEmail(generatedKeys.getInt(1), email);
        } else {
            throw new SQLException("Creating user failed, no ID obtained.");
        }
    }
}
