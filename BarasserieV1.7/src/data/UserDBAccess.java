package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exception.UserRestrictedException;
import interfaces.UserDataAccess;
import model.*;

public class UserDBAccess implements UserDataAccess {
    private static final String STARTING_STATUS = "regular";

    private Connection connection ;
    private AddressDBAccess addressDBAccess;
    private CommunicationDBAccess communicationDBAccess;
    
    public UserDBAccess() {
        addressDBAccess = new AddressDBAccess();
        communicationDBAccess = new CommunicationDBAccess();
    }

    @Override
    public void create(User user) throws SQLException {
        int addressId = addressDBAccess.addAddress(user.getAddress());

        String query = new StringBuilder()
            .append("INSERT INTO business_entity ")
            .append("(address, tier, lastname, firstname, isClient, isSupplier, registrationDate, hashedPassword, salt) ")
            .append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")
            .toString();

        connection = SingletonConnection.getInstance();

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, addressId);
            statement.setString(2, STARTING_STATUS);
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getFirstname());
            statement.setBoolean(5, true);
            statement.setBoolean(6, false);
            statement.setDate(7, new Date(System.currentTimeMillis()));
            statement.setString(8, user.getPassword());
            statement.setString(9, user.getSalt());

            int affectedRow = statement.executeUpdate();
            if (affectedRow == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    communicationDBAccess.addEmail(generatedKeys.getInt(1), user.getEmail());
                } else {
                    throw new SQLException("Creating address failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void update(User user) throws SQLException {
        String query = new StringBuilder()
            .append("UPDATE business_entity be ")
            .append("INNER JOIN communication com ON com.entity = be.id ")
            .append("INNER JOIN address a ON be.address = a.id ")
            .append("SET be.firstname = ? , be.lastname = ? , be.hashedPassword = ? , be.salt = ? , ")
            .append("a.street = ? , a.number = ? , com.communicationDetails = ? , a.city = ? ")
            .append("WHERE be.id = ? AND a.id = ? AND com.entity = ?")
            .toString();

        connection = SingletonConnection.getInstance();
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getSalt());
            statement.setString(5, user.getAddress().getStreet());
            statement.setInt(6, user.getAddress().getNumber());
            statement.setString(7, user.getEmail());
            statement.setInt(8, addressDBAccess.getAddress(user.getAddress()));
            statement.setInt(9, user.getId());
            statement.setInt(10, user.getIdAddress());
            statement.setInt(11, user.getId());
            
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(User user) throws SQLException, UserRestrictedException {
        connection = SingletonConnection.getInstance();

        if (!userHasDocument(user.getId())){
            communicationDBAccess.deleteEmail(user.getId());
            deleteUser(user.getId());
            deleteWorkflow(user.getId());
            addressDBAccess.deleteAddress(user.getIdAddress());
        }
        else {
            throw new UserRestrictedException("User has some documents");
        }
    }

    @Override
    public boolean userHasDocument(Integer idUser) throws SQLException {
        connection = SingletonConnection.getInstance();

        String query = new StringBuilder()
            .append("SELECT * FROM document d ")
            .append("INNER JOIN workflow w ON w.id = d.workflow ")
            .append("INNER JOIN business_entity be ON be.id = w.agent ")
            .append("WHERE be.id = ?")
            .toString();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUser);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }           
        }
    }


    private void deleteUser(Integer clientId) throws SQLException {
        String query = new StringBuilder()
            .append("DELETE FROM business_entity ")
            .append("WHERE id = ?")
            .toString();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            statement.executeUpdate();        
        }
    }

    private void deleteWorkflow(Integer clientId) throws SQLException {
        String query = new StringBuilder()
            .append("DELETE FROM workflow ")
            .append("WHERE agent = ?")
            .toString();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            statement.executeUpdate();
        }
    }

    @Override
    public TopProductClient getTopProduct(int userId) throws SQLException {
        TopProductClient topProductClient = new TopProductClient();

        String query = new StringBuilder()
                .append("SELECT be.firstname, be.lastname, p.name, SUM(td.quantity) AS quantity ")
                .append("FROM business_entity be ")
                .append("INNER JOIN workflow w ON be.id = w.agent ")
                .append("INNER JOIN document d ON w.id = d.workflow ")
                .append("INNER JOIN transaction_detail td ON d.docType = td.docType AND d.id = td.document ")
                .append("INNER JOIN product p ON td.product = p.id ")
                .append("WHERE be.id = ? ")
                .append("GROUP BY p.name ")
                .append("ORDER BY SUM(td.quantity) DESC ")
                .append("LIMIT 1")
                .toString();

        connection = SingletonConnection.getInstance();

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
                .append("SELECT be.id, be.firstname, be.lastname, com.communicationDetails, be.hashedPassword, a.id, be.salt, ")
                .append("a.street, a.number, a.city ,c.postalCode, c.name, c.country ")
                .append("FROM business_entity be ")
                .append("INNER JOIN address a ON be.address = a.id ")
                .append("INNER JOIN city c ON a.city = c.id ")
                .append("INNER JOIN communication com ON be.id = com.entity ")
                .append("INNER JOIN communication_type ct ON com.type = ct.type ")
                .append("WHERE ct.type = 'email'");

        if (isSoloUser) {
            query.append(" AND be.id = ?");
        }

        return query.toString();
    }

    @Override
    public User getUser(int userId) throws SQLException {
        User user = new User();
        String query = getUserQuery(true);

        connection = SingletonConnection.getInstance();

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
                            resultSet.getString("salt"),
                            resultSet.getString("street"),
                            resultSet.getInt("number"),
                            resultSet.getInt("postalCode"),
                            resultSet.getString("name"),
                            resultSet.getString("country"),
                            resultSet.getInt("a.id"));
                }
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        String query = getUserQuery(false);

        connection = SingletonConnection.getInstance();

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
                            null,
                            resultSet.getString("street"),
                            resultSet.getInt("number"),
                            resultSet.getInt("postalCode"),
                            resultSet.getString("name"),
                            resultSet.getString("country"),
                            null);
                    users.add(user);
                }
            }
        }
        return users;
    }
}