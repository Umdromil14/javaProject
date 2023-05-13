package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.UserDataAccess;
import tools.*;
import tools.DBOutput.TopProductCity;
import tools.DBOutput.TopProductClient;
import tools.DBOutput.User;

public class UserDBAccess implements UserDataAccess {
    private static final String STARTING_STATUS = "regular";
    
    public int addAddress(Address address) throws SQLException {
        String query = "SELECT id FROM city WHERE name = ? AND postalCode = ? AND country = ?";
        Connection connection = SingletonConnection.getInstance();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, address.getCity().getName());
        statement.setInt(2, address.getCity().getPostalCode());
        statement.setString(3, address.getCity().getCountry());

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

    public void addEmail(int id, String email) throws SQLException {
        String query = "INSERT INTO communication (entity, type, communicationDetails) VALUES (?, ?, ?)";

        Connection connection = SingletonConnection.getInstance();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, id);
        statement.setString(2, "email");
        statement.setString(3, email);

        statement.executeUpdate();

    }

    public void addUser(BusinessEntity bussinessEntity) throws SQLException {
        int addressId = addAddress(bussinessEntity.getAddress());

        String query = "INSERT INTO business_entity " +
        "(address, tier, lastname, firstname, isClient, isSupplier, registrationDate, hashedPassword, salt) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = SingletonConnection.getInstance();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, addressId);
        statement.setString(2, STARTING_STATUS);
        statement.setString(3, bussinessEntity.getLastname());
        statement.setString(4, bussinessEntity.getFirstname());
        statement.setBoolean(5, true);
        statement.setBoolean(6, false);
        statement.setDate(7, new Date(System.currentTimeMillis()));

        String salt = Utils.generateSalt();
        String hashedPassword = Utils.hashPassword(bussinessEntity.getPassword(), salt);
        statement.setString(8, hashedPassword);
        statement.setString(9, salt);

        int affectedRow = statement.executeUpdate();
        if (affectedRow == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            addEmail(generatedKeys.getInt(1), bussinessEntity.getEmail());
        } else {
            throw new SQLException("Creating user failed, no ID obtained.");
        }
        System.out.println("User added");
    }

    public TopProductClient getTopProduct(int userId) throws SQLException {
        TopProductClient topProductClient = new TopProductClient();

        String query = new StringBuilder()
            .append("SELECT be.firstname, be.lastname, p.name, SUM(td.quantity) AS quantity ")
            .append("FROM business_entity be ")
            .append("JOIN workflow w ON be.id = w.agent ")
            .append("JOIN document d ON w.id = d.workflow ")
            .append("JOIN transaction_detail td ON d.docType = td.docType AND d.id = td.document ")
            .append("JOIN product p ON td.product = p.id ")
            .append("WHERE be.id = ? ")
            .append("GROUP BY p.name ")
            .append("ORDER BY SUM(td.quantity) DESC ")
            .append("LIMIT 1")
            .toString();

        Connection connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    topProductClient.setTopProductClient(
                        resultSet.getString("be.firstname"),
                        resultSet.getString("be.lastname"),
                        resultSet.getString("p.name"),
                        resultSet.getInt("quantity")
                    );
                }
            }
        }

        return topProductClient;
    }

    private String getUserQuery(boolean isSoloUser) {
        StringBuilder query = new StringBuilder()
            .append("SELECT be.id, be.firstname, be.lastname, com.communicationDetails, be.hashedPassword, ")
            .append("a.street, a.number, c.postalCode, c.name, c.country ")
            .append("FROM business_entity be ")
            .append("JOIN address a ON be.address = a.id ")
            .append("JOIN city c ON a.city = c.id ")
            .append("JOIN communication com ON be.id = com.entity ")
            .append("JOIN communication_type ct ON com.type = ct.type ")
            .append("WHERE ct.type = 'email'");

        if (isSoloUser) {
            query.append(" AND be.id = ?");
        }

        return query.toString();
    }

    public User getUser(int userId) throws SQLException {
        User user = new User();

        String query = getUserQuery(true);

        Connection connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user.setUser(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("communicationDetails"),
                        resultSet.getString("hashedPassword"),
                        resultSet.getString("street"),
                        resultSet.getInt("number"),
                        resultSet.getInt("postalCode"),
                        resultSet.getString("name"),
                        resultSet.getString("country")
                    );
                }
            }
        }

        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        String query = getUserQuery(false);

        Connection connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setUser(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("communicationDetails"),
                        resultSet.getString("hashedPassword"),
                        resultSet.getString("street"),
                        resultSet.getInt("number"),
                        resultSet.getInt("postalCode"),
                        resultSet.getString("name"),
                        resultSet.getString("country")
                    );
                    users.add(user);
                }
            }
        }

        return users;
    }
}